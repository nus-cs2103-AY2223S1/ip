package duke;

/**
 * An exception that is only thrown when a Duke-specific error has occurred.
 */
public class DukeException extends Exception{
    public DukeException(String msg) {
        super(msg);
    }

    @Override
    public String toString() {
        return this.getMessage();
    }
}
