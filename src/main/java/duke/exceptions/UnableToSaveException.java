package duke.exceptions;

public class UnableToSaveException extends Exception {
    public UnableToSaveException() {
        super("Unable to save file.");
    }
}
