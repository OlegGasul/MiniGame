package com.yggdrasil.service;

import com.yggdrasil.entity.Card;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Collection;

import static java.util.Collections.EMPTY_LIST;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class GameEngineTest {
    private GameEngine gameEngine = new GameEngineImpl();

    @Test
    void calculationAwardsTest() {
        Collection<Card> cards = Arrays.asList(Card.EUR_100, Card.EUR_5, Card.GAME_OVER);
        Collection<Card> additionalCards = Arrays.asList(Card.EUR_5);
        assertEquals(110L, gameEngine.calculateAward(cards, additionalCards));

        cards = Arrays.asList(Card.EUR_100, Card.EUR_5, Card.EXTRA_LIFE, Card.GAME_OVER, Card.EUR_10, Card.GAME_OVER);
        assertEquals(120L, gameEngine.calculateAward(cards, additionalCards));

        cards = Arrays.asList(Card.EUR_100, Card.EUR_5, Card.EXTRA_LIFE, Card.GAME_OVER, Card.EUR_10, Card.GAME_OVER, Card.EUR_20);
        additionalCards = Arrays.asList(Card.SECOND_CHANCE, Card.EUR_10);
        assertEquals(145L, gameEngine.calculateAward(cards, additionalCards));

        assertEquals(0L, gameEngine.calculateAward(EMPTY_LIST, EMPTY_LIST));
    }

    @Test
    void exceptionalTest() {
        assertThrows(IllegalArgumentException.class, () -> gameEngine.calculateAward(null, EMPTY_LIST));
        assertThrows(IllegalArgumentException.class, () -> gameEngine.calculateAward(EMPTY_LIST, null));
    }

}
