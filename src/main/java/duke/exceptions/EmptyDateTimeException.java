package duke.exceptions;

public class EmptyDateTimeException extends Exception{
    public EmptyDateTimeException() {
        super("The folly of youth to cheat Time! Specify date and time to add events and deadlines...");
    }
}
