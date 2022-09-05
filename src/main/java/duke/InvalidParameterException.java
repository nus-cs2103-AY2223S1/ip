package duke;

public class InvalidParameterException extends RuntimeException {
    public InvalidParameterException() {
        super("OOPS!!! One of your parameters seems to be invalid!");
    }
}
