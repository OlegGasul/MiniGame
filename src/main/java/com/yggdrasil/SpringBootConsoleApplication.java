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
    public void run(String... args) {
        if (args.length < 2) {
            log.info("[Usage]: mvn spring-boot:run -Drun.arguments=\"<number of simulations>,<number of threads>\"");
            System.exit(0);
        }

        try {
            long simulations = Long.parseLong(args[0]);
            int threads = Integer.parseInt(args[1]);

            BigDecimal result = gameSimulationService.calculateAverageAward(simulations, threads);
            log.info("Average value of " + simulations + " simulations = " + result.doubleValue());
        } catch (NumberFormatException ex) {
            log.error("Error parsing parameters", ex);
        } catch (Exception ex) {
            log.error("Runtime error", ex);
        }
    }
}
