package com.yggdrasil.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.stream.IntStream;

@Slf4j
@Service
public class GameSimulationServiceImpl implements GameSimulationService {

    @Autowired
    private GameSimulationSupplierFactory gameSimulationSupplierFactory;

    public BigDecimal calculateAverageAward(long attempts, int threads) {
        CompletableFuture[] futures = new CompletableFuture[threads];
        IntStream.range(0, threads).forEach(i -> {
            GameSimulationRepeater supplier = gameSimulationSupplierFactory.createSupplier(attempts / threads);
            futures[i] = CompletableFuture.supplyAsync(supplier);
        });

        try {
            CompletableFuture.allOf(futures).join();

            BigDecimal result = BigDecimal.ZERO;
            for (CompletableFuture future : futures) {
                result = result.add(BigDecimal.valueOf((Double) future.get()));
            }

            return result.divide(BigDecimal.valueOf(threads));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return null;
    }
}
