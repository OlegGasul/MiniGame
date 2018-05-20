package com.yggdrasil.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.concurrent.CompletableFuture;
import java.util.stream.IntStream;

@Slf4j
@Service
public class GameSimulationServiceImpl implements GameSimulationService {

    @Autowired
    private GameSimulationRepeaterFactory gameSimulationRepeaterFactory;

    public BigDecimal calculateAverageAward(long attempts, int threads) {
        CompletableFuture<BigDecimal>[] futures = new CompletableFuture[threads];
        long repeats = attempts / threads;
        IntStream.range(0, threads).forEach(i -> {
            GameSimulationRepeater supplier = gameSimulationRepeaterFactory.createSupplier(repeats);
            futures[i] = CompletableFuture.supplyAsync(supplier);
        });

        try {
            CompletableFuture.allOf(futures).join();

            BigDecimal result = BigDecimal.ZERO;
            for (CompletableFuture<BigDecimal> future : futures) {
                result = result.add(future.get());
            }

            return result.divide(BigDecimal.valueOf(threads));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }
}
