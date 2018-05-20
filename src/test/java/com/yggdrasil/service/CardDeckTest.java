package com.yggdrasil.service;

import com.yggdrasil.entity.Card;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class CardDeckTest {
    private CardDeckFactory cardDeckFactory = new CardDeckFactoryImpl();

    @Test
    void shouldBeNeededAmountOfCardsTest() {
        Collection<Card> cards = cardDeckFactory.generateCards();
        Collection<Card> additionalCards = cardDeckFactory.generateAdditionalCards();

        assertEquals(12, cards.size());
        assertEquals(4, additionalCards.size());

        Map<Card, Long> result = cards.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        assertEquals(Long.valueOf(1), result.get(Card.EUR_100));
        assertEquals(Long.valueOf(2), result.get(Card.EUR_20));
        assertEquals(Long.valueOf(5), result.get(Card.EUR_5));
        assertEquals(Long.valueOf(1), result.get(Card.EXTRA_LIFE));
        assertEquals(Long.valueOf(3), result.get(Card.GAME_OVER));

        result = additionalCards.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        assertEquals(Long.valueOf(1), result.get(Card.EUR_5));
        assertEquals(Long.valueOf(1), result.get(Card.EUR_10));
        assertEquals(Long.valueOf(1), result.get(Card.EUR_20));
        assertEquals(Long.valueOf(1), result.get(Card.SECOND_CHANCE));
    }

    @Test
    void shuffleCardsTest() {
        Collection<Card> cards1 = cardDeckFactory.generateCards();
        Collection<Card> cards2 = cardDeckFactory.generateCards();

        assertNotEquals(cards1, cards2);
    }
}
