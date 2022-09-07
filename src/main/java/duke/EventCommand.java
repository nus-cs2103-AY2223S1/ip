package duke;

/**
 * Encapsulates a user command to create an event.
 */
public class EventCommand extends Command {
    private Ui ui;
    private TaskList tasks;
    private String userResponse;

    /**
     * Constructor for a <code>EventCommand</code>.
     *
     * @param ui The user interface for Duke.
     * @param tasks The list of current tasks.
     * @param userResponse The input string from the user.
     */
    public EventCommand(Ui ui, TaskList tasks, String userResponse) {
        this.ui = ui;
        this.tasks = tasks;
        this.userResponse = userResponse;
    }

    /**
     * Executes the given command.
     *
     * @return A string encapsulating the response from Duke.
     */
    @Override
    public String execute() {
        this.tasks.addTasks(Parser.parseEventTask(userResponse));
        return this.ui.showAddTaskSuccess(this.tasks);
    }
}
