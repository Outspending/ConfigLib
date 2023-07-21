package me.outspending.configlib;

import java.util.function.Supplier;

public final class Timer {

    private long amountOfTime = 0;

    /**
     * Starts a timer
     *
     * @param supplier
     * @return
     */
    public Timer start(Supplier<?> supplier) {
        long start = System.currentTimeMillis();
        supplier.get();
        amountOfTime = System.currentTimeMillis() - start;
        return this;
    }

    /**
     * Gets the amount of time it took to run the supplier
     *
     * @return
     */
    public long get() {
        return amountOfTime;
    }
}
