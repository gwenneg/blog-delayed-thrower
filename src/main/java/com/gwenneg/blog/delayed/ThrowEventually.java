package com.gwenneg.blog.delayed;

import java.util.concurrent.atomic.AtomicInteger;

public class ThrowEventually {

    private final AtomicInteger iterations = new AtomicInteger();

    public void run() {
        DelayedThrower.throwEventually("Exceptions were thrown while looping", exceptions -> {
            for (int i = 0; i < 10; i++) {
                try {
                    if (i % 3 == 2) {
                        throw new RuntimeException(String.format("Something went wrong [i=%d]", i));
                    }
                } catch (Exception e) {
                    exceptions.add(e);
                }
                iterations.incrementAndGet();
            }
        });
    }

    public AtomicInteger getIterations() {
        return iterations;
    }
}
