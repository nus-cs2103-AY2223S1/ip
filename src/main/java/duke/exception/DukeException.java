package duke.exception;
/**
 * Encapsulates the errors that occur that are specific to the ChatBot/Duke mainly
 * due to invalid inputs from the user. Invalid tasks number specified, unrecognised
 * commands, empty TaskList during Marking/Unmarking/Deleting.
 */
public class DukeException extends Exception {
    public DukeException(String errorMessage) {
        super(errorMessage);
    }
}
