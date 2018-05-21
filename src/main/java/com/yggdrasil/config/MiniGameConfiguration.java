package com.yggdrasil.config;

import com.yggdrasil.service.GameRepeater;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class MiniGameConfiguration {
    @Bean
    @Scope(value = "prototype")
    public GameRepeater gameRepeater() {
        return new GameRepeater();
    }
}
