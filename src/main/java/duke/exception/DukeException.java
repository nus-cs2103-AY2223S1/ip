package duke.exception;
/**
 * Encapsulates the errors that occur that are specific to the ChatBot/Duke mainly
 * due to invalid inputs from the user. Invalid tasks number specified, unrecognised
 * commands, empty TaskList during Marking/Unmarking/Deleting.
 *
 * @author bensohh
 * @version CS2103T AY 22/23 Sem 1 (G01)
 */
public class DukeException extends Exception {
    public DukeException(String errorMessage) {
        super(errorMessage);
    }
}
