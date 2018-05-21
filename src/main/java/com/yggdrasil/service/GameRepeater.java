package com.yggdrasil.service;

import org.springframework.beans.factory.annotation.Autowired;
import java.math.BigDecimal;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class GameRepeater implements Supplier<BigDecimal> {
    @Autowired
    private CardDeckFactory cardDeckFactory;

    @Autowired
    private GameEngine gameEngine;

    private long repeats;

    @Override
    public BigDecimal get() {
        return Stream.generate(() -> gameEngine.calculateAward(cardDeckFactory.generateCards(), cardDeckFactory.generateAdditionalCards()))
            .limit(repeats)
            .parallel()
            .map(BigDecimal::valueOf)
            .reduce(BigDecimal.ZERO, BigDecimal::add)
            .divide(BigDecimal.valueOf(repeats));
    }

    public void setRepeats(long repeats) {
        this.repeats = repeats;
    }
}
