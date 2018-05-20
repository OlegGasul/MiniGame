package com.yggdrasil.service;

import com.yggdrasil.entity.Card;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static com.yggdrasil.entity.Card.*;

@Service
public class CardDeckFactoryImpl implements CardDeckFactory {
    public ArrayList<Card> generateCards() {
        ArrayList<Card> cards = new ArrayList<>(Arrays.asList(EUR_100,
                EUR_20, EUR_20,
                EUR_5, EUR_5, EUR_5, EUR_5, EUR_5,
                EXTRA_LIFE,
                GAME_OVER, GAME_OVER, GAME_OVER));
        Collections.shuffle(cards);

        return cards;
    }

    public ArrayList<Card> generateAdditionalCards() {
        ArrayList<Card> cards = new ArrayList<>(Arrays.asList(EUR_5, EUR_10, EUR_20, SECOND_CHANCE));
        Collections.shuffle(cards);

        return cards;
    }
}
