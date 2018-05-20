package com.yggdrasil.service;

public interface GameSimulationRepeaterFactory {
    GameSimulationRepeater createSupplier(long repeats);
}
