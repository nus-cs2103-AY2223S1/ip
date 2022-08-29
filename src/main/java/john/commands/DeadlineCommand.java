package john.commands;

/**
 * Represents a command to create a deadline task.
 */
public class DeadlineCommand extends Command {
    public static final String COMMAND_WORD = "deadline";
    public static final String FORMAT = "deadline <description> /by <dd/mm/yyyy> <hhmm | optional>";

    private String description;
    private String timing;

    /**
     * Constructor for a Deadline command.
     * @param params The parameters of the deadline, including the description and timing.
     */
    public DeadlineCommand(String params) {
        String[] deadlineParams = params.split(" /by ", 2);
        this.description = deadlineParams[0];
        this.timing = deadlineParams[1];
    }

    /**
     * Adds a deadline task to the task list.
     * @return A string representation of the deadline added.
     */
    @Override
    public String execute() {
        String task = tasklist.addDeadline(this.description, this.timing);
        return ui.showAddedTask(task, tasklist);
    }
}
