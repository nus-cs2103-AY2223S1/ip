package duke.commands;

import duke.exceptions.DukeException;
import duke.exceptions.WrongTodoFormatException;
import duke.task.Todo;
import duke.undo.TaskUndo;

/**
 * Represents a todo command in the Duke application.
 */
public class TodoCommand extends Command {
    /** Command word of the todo command. */
    public static final String COMMAND_WORD = "todo";
    private static final String USER_MESSAGE_FORMAT = "Added this todo!\n  %s\nNow you have %d tasks.";
    private final Todo todo;

    /**
     * Constructs a todo command with arguments.
     *
     * @param arguments Arguments string is to be of the format "description".
     * @throws DukeException Exception due to invalid arguments.
     */
    public TodoCommand(String arguments) throws DukeException {
        if (arguments.length() < 1) {
            throw new WrongTodoFormatException();
        }

        todo = new Todo(arguments);
    }

    @Override
    public CommandResult execute() {
        assert tasks != null : "Should setData() before calling execute().";
        tasks.addTask(todo);
        TaskUndo undoAction = new TaskUndo(todo);
        int numberOfTasks = tasks.size();
        String userMessage = String.format(USER_MESSAGE_FORMAT, todo, numberOfTasks);
        return new CommandResult(userMessage, CommandResult.Action.UPDATE_FILE, undoAction);
    }
}
