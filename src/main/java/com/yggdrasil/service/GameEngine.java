package com.yggdrasil.service;

import com.yggdrasil.entity.Card;

import java.util.Collection;

public interface GameEngine {
    long calculateAward(Collection<Card> cards, Collection<Card> additionalCards);
}
