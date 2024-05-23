package duke.exceptions;

/** Represents parent DukeException class */
public abstract class DukeException extends Exception {
    public DukeException(String msg) {
        super(msg);
    }
}
