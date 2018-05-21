package com.yggdrasil.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Service
public class GameSimulationServiceImpl implements GameSimulationService {

    @Autowired
    private GameRepeaterFactory gameRepeaterFactory;

    public BigDecimal calculateAverageAward(long simulations, int threads) {
        if (simulations <= 0) {
            throw new IllegalArgumentException("Simulations should be 1 or greater");
        }
        if (threads <= 0) {
            throw new IllegalArgumentException("Number of threads should be 1 or greater");
        }

        long repeats = simulations / threads;

        Supplier<List<CompletableFuture<BigDecimal>>> supplier = () -> new LinkedList<>();
        CompletableFuture<BigDecimal>[] futures = Stream.generate(() -> CompletableFuture.supplyAsync(gameRepeaterFactory.createRepeater(repeats)))
                .limit(threads)
                .collect(Collectors.toCollection(supplier))
                .toArray(new CompletableFuture[threads]);

        try {
            CompletableFuture.allOf(futures).join();

            return Arrays.stream(futures)
                .map((future) -> future.getNow(BigDecimal.ZERO))
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .divide(BigDecimal.valueOf(threads));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }
}
