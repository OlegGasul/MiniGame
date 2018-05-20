package com.yggdrasil.service;

import com.yggdrasil.entity.Card;
import java.util.Collection;

public interface CardDeckFactory {
    Collection<Card> generateCards();
    Collection<Card> generateAdditionalCards();
}
