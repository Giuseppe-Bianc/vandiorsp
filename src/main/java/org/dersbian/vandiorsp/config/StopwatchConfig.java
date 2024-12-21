package org.dersbian.vandiorsp.config;

import org.dersbian.vandiorsp.util.Stopwatch;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StopwatchConfig {

    @Bean
    public Stopwatch stopwatch() {
        return new Stopwatch("global");
    }
}