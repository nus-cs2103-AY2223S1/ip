package ava.exception;

import java.time.format.DateTimeFormatter;

/**
 * Class to represent Duke.Exception.NoCommandException.
 */
public class NoCommandException extends AvaException {
    public NoCommandException() {
        super("Why is there no command?");
    }
}
