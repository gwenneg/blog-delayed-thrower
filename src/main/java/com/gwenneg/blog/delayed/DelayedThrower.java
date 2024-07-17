package com.gwenneg.blog.delayed;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

public class DelayedThrower {

    public static void throwEventually(String exceptionMessage, Consumer<List<Exception>> exceptionsConsumer) {
        Objects.requireNonNull(exceptionsConsumer, "The exceptions consumer must be not null");
        List<Exception> exceptions = new ArrayList<>();
        exceptionsConsumer.accept(exceptions);
        if (!exceptions.isEmpty()) {
            DelayedException delayedException = new DelayedException(exceptionMessage);
            for (Exception e : exceptions) {
                if (e instanceof DelayedException && e.getSuppressed().length > 0) {
                    for (Throwable t : e.getSuppressed()) {
                        delayedException.addSuppressed(t);
                    }
                } else {
                    delayedException.addSuppressed(e);
                }
            }
            throw delayedException;
        }
    }
}
