package org.dersbian.vandiorsp.util;


import lombok.extern.slf4j.Slf4j;
import java.time.Duration;
import java.time.Instant;

@Slf4j
public class Stopwatch {
    private static final long TIME_FACTOR = 1_000;
    private static final long TIME_FACTOR2 = 1_000_000_000L;
    private static final long TIME_FACTOR3 = 1_000_000L;
    private static final long TIME_FACTOR4 = 60;
    private Instant startTime;
    private Duration elapsedTime = Duration.ZERO;
    private boolean running;
    private final String name;

    public Stopwatch(String name) {
        this.name = name;
    }

    public synchronized void start() {
        if (!running) {
            startTime = Instant.now();
            running = true;
        } else {
            log.warn("Stopwatch '{}' is already running.", name);
        }
    }

    public synchronized void stop() {
        if (running) {
            elapsedTime = elapsedTime.plus(Duration.between(startTime, Instant.now()));
            running = false;
            displayTime();
            elapsedTime = Duration.ZERO;
        } else {
            log.warn("Stopwatch '{}' is not running.", name);
        }
    }

    /*public synchronized void reset() {
        elapsedTime = Duration.ZERO;
        running = false;
        log.info("Stopwatch '{}' reset.", name);
    }*/


    /*
    long elapsedNanos = totalNanos / 1_000_000_000L;
        long minutes = elapsedNanos / 60;
        long seconds = elapsedNanos % 60;
        long milliseconds = (totalNanos / 1_000_000L) % 1_000;
        long microseconds = (totalNanos / 1_000L) % 1_000;
        long nanoseconds = totalNanos % 1_000;
    */
    public void displayTime() {
        long totalNanos = elapsedTime.toNanos();

        long totalSeconds = totalNanos / TIME_FACTOR2;
        long totalMilliseconds = totalNanos / TIME_FACTOR3;
        long totalMicroseconds = totalNanos / TIME_FACTOR;

        long minutes = totalSeconds / TIME_FACTOR4;

        long seconds = totalSeconds % TIME_FACTOR4;

        long milliseconds = totalMilliseconds % TIME_FACTOR;

        long microseconds = totalMicroseconds % TIME_FACTOR;

        long nanoseconds = totalNanos % TIME_FACTOR;


        if (minutes > 0) {
            log.info("{} Tempo trascorso: {}m, {}s, {}ms, {}us, {}ns", name, minutes, seconds, milliseconds, microseconds, nanoseconds);
        } else {
            log.info("{} Tempo trascorso: {}s, {}ms, {}us, {}ns", name, seconds, milliseconds, microseconds, nanoseconds);
        }
    }
}
