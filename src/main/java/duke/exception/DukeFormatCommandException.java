package duke.exception;

/**
 * Thrown to indicate that the input command format is invalid.
 *
 * @author Justin Peng
 */
public class DukeFormatCommandException extends DukeException {
    /**
     * Constructs a {@code DukeFormatCommandException} with an argument indicating the command that the user is
     * trying to use.
     *
     * @param command The command that the user is trying to use.
     */
    public DukeFormatCommandException(String command) {
        super(String.format("☹ OOPS!!! The description of %s cannot be empty.", command));
    }

    /**
     * Constructs a {@code DukeFormatCommandException} with arguments indicating the task type that the user is
     * trying to create and the parameter that is missing or invalid.
     *
     * @param taskType The task type that the user is trying to create.
     * @param separator The parameter that is missing or invalid.
     */
    public DukeFormatCommandException(String taskType, String separator) {
        super(String.format("☹ OOPS!!! The description of %s requires %s value in the format "
                + "\"dd/MM/yyyy\" or \"dd/MM/yyyy HH:mm\".", taskType, separator));
    }
}
