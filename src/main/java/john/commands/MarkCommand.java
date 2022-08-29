package john.commands;

/**
 * Represents a command to mark a specified task.
 */
public class MarkCommand extends Command {
    public static final String COMMAND_WORD = "mark";
    public static final String FORMAT = "mark <integer>";

    private final String markParams;

    /**
     * Constructor for a MarkCommand.
     * @param markParams The position of the task to mark.
     */
    public MarkCommand(String markParams) {
        this.markParams = markParams;
    }

    /**
     * Marks the specified task in the task list.
     * @return A string representation of the task marked.
     */
    @Override
    public String execute() {
        return tasklist.markTask(this.markParams);
    }
}
