package com.gwenneg.blog.delayed;

import java.util.concurrent.atomic.AtomicInteger;

public class HandleAndContinue {

    private final AtomicInteger iterations = new AtomicInteger();

    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                if (i % 3 == 2) {
                    throw new RuntimeException("Something went wrong");
                }
            } catch (Exception e) {
                // TODO Handle the exception.
            }
            iterations.incrementAndGet();
        }
    }

    public AtomicInteger getIterations() {
        return iterations;
    }
}
