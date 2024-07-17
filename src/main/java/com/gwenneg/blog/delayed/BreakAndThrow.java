package com.gwenneg.blog.delayed;

public class BreakAndThrow {

    public void run() {
        for (int i = 0; i < 10; i++) {
            if (i % 3 == 2) {
                throw new RuntimeException("Something went wrong");
            }
        }
    }
}
