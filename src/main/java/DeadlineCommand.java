/**
 * This class encapsulates a deadline command from the user.
 */
public class DeadlineCommand extends AddCommand {
    public static final String COMMAND_WORD = "deadline";

    DeadlineCommand(TaskList taskList, Deadline deadline) {
        super(taskList, deadline);
    }
}
