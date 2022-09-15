package Duke.Exceptions;

/**
 * Class that denotes the Exception for invalid Deadline command.
 */
public class DeadlineException extends DukeException {
    @Override
    public String toString() {
        return "Sorry, the format of Deadline command could be wrong ... \nPlease check again : )";
    }
}
