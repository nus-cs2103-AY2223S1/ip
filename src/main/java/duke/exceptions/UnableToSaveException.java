package duke.exceptions;

/** Represents an exception when file is unable to save.*/
public class UnableToSaveException extends Exception {
    public UnableToSaveException() {
        super("Unable to save file.");
    }
}
