package arc;

public class EmptyTitleException extends DukeException {
    /**
     * Constructor for aRC.EmptyTitleException
     */
    public EmptyTitleException() {
        super("Title cannot be empty :-(");
    }
}
