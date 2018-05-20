package com.yggdrasil.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.OptionalDouble;
import java.util.function.Supplier;
import java.util.stream.LongStream;

@Component
public class GameSimulationRepeater implements Supplier<Double> {
    @Autowired
    private CardDeckFactory cardDeckFactory;

    @Autowired
    private GameEngine gameEngine;

    private long repeats;

    @Override
    public Double get() {
        OptionalDouble optionalDouble = LongStream.range(1, repeats)
                .map(i -> gameEngine.calculateAward(cardDeckFactory.generateCards(), cardDeckFactory.generateAdditionalCards()) )
                .average();
        return optionalDouble.isPresent() ? optionalDouble.getAsDouble() : null;
    }

    public void setRepeats(long repeats) {
        this.repeats = repeats;
    }
}
