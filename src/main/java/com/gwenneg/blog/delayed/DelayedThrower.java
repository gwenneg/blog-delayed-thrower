package com.gwenneg.blog.delayed;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

public class DelayedThrower {

    public static void throwEventually(String exceptionMessage, Consumer<List<Exception>> exceptionsConsumer) {

        Objects.requireNonNull(exceptionsConsumer, "The exceptions consumer must be not null");

        // Exceptions will be stored into that collection until the loop completes.
        List<Exception> exceptions = new ArrayList<>();
        exceptionsConsumer.accept(exceptions);

        if (exceptions.isEmpty()) {
            // If no exceptions were thrown, the method can exit immediately.
            return;
        }

        // This is the exception that will eventually be thrown.
        DelayedException delayedException = new DelayedException(exceptionMessage);

        for (Exception e : exceptions) {
            // This makes it possible to use nested DelayedThrower#throwEventually calls.
            if (e instanceof DelayedException && e.getSuppressed().length > 0) {
                for (Throwable t : e.getSuppressed()) {
                    // The exceptions thrown from the loop are stored as suppressed exceptions of the DelayedException.
                    delayedException.addSuppressed(t);
                }
            } else {
                // The exceptions thrown from the loop are stored as suppressed exceptions of the DelayedException.
                delayedException.addSuppressed(e);
            }
        }

        throw delayedException;
    }
}
