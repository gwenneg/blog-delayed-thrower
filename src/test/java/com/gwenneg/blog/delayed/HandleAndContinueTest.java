package com.gwenneg.blog.delayed;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HandleAndContinueTest {

    @Test
    void test() {

        HandleAndContinue handleAndContinue = new HandleAndContinue();
        handleAndContinue.run();

        assertEquals(10, handleAndContinue.getIterations().get());
    }
}
