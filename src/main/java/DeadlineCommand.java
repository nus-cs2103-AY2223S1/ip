import java.time.DateTimeException;

/**
 * This class encapsulates a deadline command from the user.
 */
public class DeadlineCommand extends AddCommand {
    public static final String COMMAND_WORD = "deadline";
    public static final String COMMAND_SEPARATOR = "/by";

    DeadlineCommand(TaskList taskList, Deadline deadline) {
        super(taskList, deadline);
    }
}
