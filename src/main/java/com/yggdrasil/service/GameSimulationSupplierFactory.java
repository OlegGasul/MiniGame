package com.yggdrasil.service;

public interface GameSimulationSupplierFactory {
    GameSimulationRepeater createSupplier(long repeats);
}
