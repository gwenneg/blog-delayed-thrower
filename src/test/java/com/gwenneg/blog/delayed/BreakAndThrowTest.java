package com.gwenneg.blog.delayed;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BreakAndThrowTest {

    @Test
    void test() {

        BreakAndThrow breakAndThrow = new BreakAndThrow();
        RuntimeException e = assertThrows(RuntimeException.class, () -> breakAndThrow.run());

        assertEquals("Something went wrong", e.getMessage());
    }
}
