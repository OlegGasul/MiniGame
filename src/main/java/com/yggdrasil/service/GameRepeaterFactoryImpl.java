package com.yggdrasil.service;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameRepeaterFactoryImpl implements GameRepeaterFactory {
    @Autowired
    private BeanFactory beanFactory;

    @Override
    public GameRepeater createRepeater(long repeats) {
        GameRepeater repeater = beanFactory.getBean(GameRepeater.class);
        repeater.setRepeats(repeats);

        return repeater;
    }
}
