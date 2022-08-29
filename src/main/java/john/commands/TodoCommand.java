package john.commands;

/**
 * Represents a command to create a to do task.
 */
public class TodoCommand extends Command {
    public static final String COMMAND_WORD = "todo";
    public static final String FORMAT = "todo <description>";

    private final String params;

    /**
     * Constructor for a TodoCommand.
     * @param params The description of the to do to add.
     */
    public TodoCommand(String params) {
        this.params = params;
    }

    /**
     * Adds a to do task to the task list.
     * @return A string representation of the to do task.
     */
    @Override
    public String execute() {
        return this.tasklist.addTodo(this.params);
    }
}
