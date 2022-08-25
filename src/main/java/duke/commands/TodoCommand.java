package duke.commands;

import duke.exceptions.DukeException;
import duke.task.Todo;

/**
 * Represents a todo command in the Duke application.
 */
public class TodoCommand extends Command {
    /** Command word of the todo command. */
    public static final String COMMAND_WORD = "todo";
    private static final String USER_MESSAGE_FORMAT = "Added this todo!\n  %s\nNow you have %d tasks.";
    private final Todo todo;

    /**
     * Constructor for a todo command that takes in arguments.
     *
     * @param arguments Arguments string is to be of the format "description".
     * @throws DukeException Exception due to invalid arguments.
     */
    public TodoCommand(String arguments) throws DukeException {
        if (arguments.length() < 1) {
            throw Todo.EMPTY_DESCRIPTION;
        }

        todo = new Todo(arguments);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CommandResult execute() {
        tasks.addTask(todo);
        int numberOfTasks = tasks.size();
        String userMessage = String.format(USER_MESSAGE_FORMAT, todo, numberOfTasks);
        return new CommandResult(userMessage, true, false);
    }
}
