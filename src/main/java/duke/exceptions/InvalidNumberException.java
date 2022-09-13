package duke.exceptions;

/**
 * Representation of an exception where user specified an index that is out of bound of taskList.
 */
public class InvalidNumberException extends DukeException {

    public InvalidNumberException() {
        super("PLEASE PROVIDE A VALID NUMBER!");
    }
}
