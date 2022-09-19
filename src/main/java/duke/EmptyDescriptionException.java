package duke;

/**
 * EmptyDescriptionException is a RuntimeException that is thrown when the user fails to provide a description.
 *
 * @author Jet Lee
 * @version CS2103T AY22/23 Sem 1
 */
public class EmptyDescriptionException extends DukeException {
    /**
     * Constructor for EmptyDescriptionException.
     *
     * @param type Command the user has input.
     */
    public EmptyDescriptionException(String type) {
        super(String.format("The description of %s cannot be empty.", type));
    }
}
