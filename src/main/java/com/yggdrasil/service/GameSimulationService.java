package com.yggdrasil.service;

import java.math.BigDecimal;

public interface GameSimulationService {
    BigDecimal calculateAverageAward(long attempts, int threads);
}
