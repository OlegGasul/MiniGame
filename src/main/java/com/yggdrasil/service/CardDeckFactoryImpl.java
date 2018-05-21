package com.yggdrasil.service;

import com.yggdrasil.entity.Card;
import org.springframework.stereotype.Service;
import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.ThreadLocalRandom;

import static com.yggdrasil.entity.Card.*;

@Service
public class CardDeckFactoryImpl implements CardDeckFactory {
    public Collection<Card> generateCards() {
        return shuffle(Arrays.asList(EUR_100,
                EUR_20, EUR_20,
                EUR_5, EUR_5, EUR_5, EUR_5, EUR_5,
                EXTRA_LIFE,
                GAME_OVER, GAME_OVER, GAME_OVER));
    }

    public Collection<Card> generateAdditionalCards() {
        return shuffle(Arrays.asList(EUR_5, EUR_10, EUR_20, SECOND_CHANCE));
    }

    private Collection<Card> shuffle(Collection<Card> cards) {
        Card[] arr = cards.toArray(new Card[cards.size()]);
        for (int i = arr.length - 1; i >= 1; i--) {
            int j = ThreadLocalRandom.current().nextInt(0, i);

            Card klop = arr[j];
            arr[j] = arr[i];
            arr[i] = klop;
        }

        return Arrays.asList(arr);
    }
}
