package duke.exception;

public class IllegalDateFormatException extends IllegalInputException {
    public IllegalDateFormatException() {
        super("OOPS!!! I could not recognised the date you entered.");
    }
}
