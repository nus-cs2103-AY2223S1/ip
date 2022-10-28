package duke.command;

import duke.task.TaskList;
import duke.task.Todo;

/**
 * Represents a todo command
 */
public class TodoCommand implements Command {
    public static final String COMMAND_WORD = "todo";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Add a todo task. Parameters: DESCRIPTION. Example: "
            + COMMAND_WORD + " borrow book";
    public static final String REGEX = "(.+)";

    private String description;

    /**
     * Constructor for a {@link TodoCommand}
     *
     * @param description Description for the event
     */
    public TodoCommand(String description) {
        this.description = description;
    }

    /**
     * Executes a command
     *
     * @param taskList
     */
    @Override
    public String execute(TaskList taskList) {
        Todo todo = new Todo(description);
        return taskList.add(todo);
    }
}
