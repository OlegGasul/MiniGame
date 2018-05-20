package com.yggdrasil.service;

import com.yggdrasil.entity.Card;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Iterator;

@Service
public class GameEngineImpl implements GameEngine {

    @Override
    public long calculateAward(Collection<Card> cards, Collection<Card> additionalCards) throws IllegalArgumentException {
        if (cards == null) {
            throw new IllegalArgumentException("Cards are null!");
        }
        if (additionalCards == null) {
            throw new IllegalArgumentException("Additional cards are null!");
        }

        long sum = 0L;

        Iterator<Card> cardsIterator = cards.iterator();
        Iterator<Card> additionalCardsIterator = additionalCards.iterator();

        boolean secondChance = false;

        do {
            boolean gameOver = false;
            boolean extraLife = false;

            while (!gameOver && cardsIterator.hasNext()) {
                Card card = cardsIterator.next();

                switch (card) {
                    case EXTRA_LIFE:
                        extraLife = true;
                        break;
                    case GAME_OVER:
                        if (extraLife) {
                            extraLife = false;
                        } else {
                            gameOver = true;
                        }
                        break;

                    default:
                        sum += card.getAward();
                }
            }

            if (additionalCardsIterator.hasNext()) {
                Card card = additionalCardsIterator.next();
                secondChance = card.equals(Card.SECOND_CHANCE);
                sum += card.getAward();
            }
        } while (secondChance);

        return sum;
    }
}
