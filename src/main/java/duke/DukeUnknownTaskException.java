package duke;

/**
 * If task is unknown, DukeUnknownCommandException
 * will be thrown.
 */
public class DukeUnknownTaskException extends DukeException {

    public DukeUnknownTaskException() {
        super("Oops sorry, I do not understand what that means!");
    }
}
