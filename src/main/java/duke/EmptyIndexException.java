package duke;

/**
 * EmptyIndexException is a RuntimeException that is thrown when the user fails to provide an index.
 *
 * @author Jet Lee
 * @version CS2103T AY22/23 Sem 1
 */
public class EmptyIndexException extends DukeException {
    /**
     * Constructor for EmptyIndexException.
     *
     * @param type Command the user has input.
     */
    public EmptyIndexException(String type) {
        super(String.format("The index to %s cannot be empty.", type));
    }
}
