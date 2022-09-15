package duke.exceptions;

/**
 * Class that denotes the Exception for invalid Event command.
 */
public class EventException extends DukeException {
    @Override
    public String toString() {
        return "Sorry, the format of Event command could be wrong ... \nPlease check again : )";
    }
}
