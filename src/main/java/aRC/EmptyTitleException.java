package aRC;

/**
 * Encapsulates a DukeException that handles empty task titles
 */
public class EmptyTitleException extends DukeException {
    /**
     * Constructor for aRC.EmptyTitleException
     */
    public EmptyTitleException() {
        super("Title cannot be empty :-(");
    }
}
