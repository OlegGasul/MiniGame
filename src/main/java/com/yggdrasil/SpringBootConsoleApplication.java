package com.yggdrasil;

import com.yggdrasil.service.GameSimulationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.ComponentScan;

import java.math.BigDecimal;

@Slf4j
@ComponentScan("com.yggdrasil.service")
public class SpringBootConsoleApplication implements CommandLineRunner {
    @Autowired
    private GameSimulationService gameSimulationService;

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(SpringBootConsoleApplication.class);
        app.setBannerMode(Banner.Mode.OFF);
        app.run(args);
    }

    @Override
    public void run(String... strings) {
        String argAttempt = strings[0];
        long attempts = 10_000_000L;
        int threads = 1;

        BigDecimal result = gameSimulationService.calculateAverageAward(attempts, threads);
        log.info("Average value of " + attempts + " attempts = " + result.doubleValue());
    }
}
