package org.dersbian.vandiorsp.util;


import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Stopwatch {
    private static final long NANOS_IN_SECOND = 1_000_000_000L;
    private static final long NANOS_IN_MILLISECOND = 1_000_000L;
    private static final long NANOS_IN_MICROSECOND = 1_000L;
    private static final long NANOS_IN_MINUTE = 60 * NANOS_IN_SECOND;
    private long startTime;
    private long elapsedTime;
    private boolean running;
    private final String name;

    public Stopwatch(String name) {
        this.name = name;
    }

    public void start() {
        if (!running) {
            startTime = System.nanoTime();
            running = true;
        } else {
            log.warn("Il cronometro è già in esecuzione.");
        }
    }

    public void stop() {
        if (running) {
            elapsedTime += System.nanoTime() - startTime;
            running = false;
            displayTime();
        } else {
            log.warn("Il cronometro non è in esecuzione.");
        }
    }

    public void reset() {
        elapsedTime = 0;
        if (running) {
            startTime = System.nanoTime();
        }
        displayTime();
    }

    public void displayTime() {
        long totalElapsedTime = running ? elapsedTime + (System.nanoTime() - startTime) : elapsedTime;

        long minutes = totalElapsedTime / NANOS_IN_MINUTE;
        long remainingAfterMinutes = totalElapsedTime % NANOS_IN_MINUTE;

        long seconds = remainingAfterMinutes / NANOS_IN_SECOND;
        long remainingAfterSeconds = remainingAfterMinutes % NANOS_IN_SECOND;

        long milliseconds = remainingAfterSeconds / NANOS_IN_MILLISECOND;
        long remainingAfterMilliseconds = remainingAfterSeconds % NANOS_IN_MILLISECOND;

        long microseconds = remainingAfterMilliseconds / NANOS_IN_MICROSECOND;
        long nanoseconds = remainingAfterMilliseconds % NANOS_IN_MICROSECOND;

        if (minutes > 0) {
            log.info("{} Tempo trascorso: {}m, {}s, {}ms, {}us, {}ns", name,minutes, seconds, milliseconds, microseconds, nanoseconds);
        } else {
            log.info("{} Tempo trascorso: {}s, {}ms, {}us, {}ns", name, seconds, milliseconds, microseconds, nanoseconds);
        }
    }
}
