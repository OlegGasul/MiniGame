package com.yggdrasil.service;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameSimulationRepeaterFactoryImpl implements GameSimulationRepeaterFactory {
    @Autowired
    private BeanFactory beanFactory;

    @Override
    public GameSimulationRepeater createSupplier(long repeats) {
        GameSimulationRepeater repeater = beanFactory.getBean(GameSimulationRepeater.class);
        repeater.setRepeats(repeats);

        return repeater;
    }
}
