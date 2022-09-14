package duke.exceptions;

/**
 * Representation of an exception where user did not input date of deadline/ event.
 */
public class NoDateException extends DukeException {

    public NoDateException() {
        super("YOU DID NOT PROVIDE A DATE!");
    }
}
