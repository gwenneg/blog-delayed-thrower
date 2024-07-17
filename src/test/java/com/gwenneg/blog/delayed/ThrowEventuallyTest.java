package com.gwenneg.blog.delayed;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ThrowEventuallyTest {

    @Test
    void test() {

        ThrowEventually throwEventually = new ThrowEventually();
        DelayedException e = assertThrows(DelayedException.class, () -> throwEventually.run());

        assertEquals("Exceptions were thrown while looping", e.getMessage());
        assertEquals(10, throwEventually.getIterations().get());
        assertEquals(3, e.getSuppressed().length);
        for (int i = 0; i < 3; i++) {
            assertEquals(RuntimeException.class, e.getSuppressed()[i].getClass());
            assertEquals("Something went wrong", e.getSuppressed()[i].getMessage());
        }
    }
}
