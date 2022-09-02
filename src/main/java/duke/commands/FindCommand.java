package duke.commands;

import java.util.ArrayList;

import duke.exceptions.DukeException;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Represents a find command in the Duke application.
 */
public class FindCommand extends Command {
    /** Command word of the find command. */
    public static final String COMMAND_WORD = "find";
    private static final String TASK_FORMAT = "%d: %s";
    private static final String USER_MESSAGE_FORMAT = "Here are the matching tasks in your list!\n%s";
    private static final DukeException WRONG_FORMAT =
        new DukeException("Wrong format for Find!\nShould be 'find <keyword>'.");
    private final String keyword;

    /**
     * Constructor for a delete command that takes in arguments.
     *
     * @param arguments Arguments string is the find keyword.
     * @throws DukeException Exception due to invalid arguments.
     */
    public FindCommand(String arguments) throws DukeException {
        if (arguments.isEmpty()) {
            throw WRONG_FORMAT;
        }
        keyword = arguments;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CommandResult execute() {
        ArrayList<String> foundTasks = new ArrayList<>();
        TaskList filteredTasks = tasks.filter(task -> task.containsKeyword(keyword));

        for (int i = 0; i < filteredTasks.size(); i++) {
            Task task = filteredTasks.getTask(i);
            String taskString = String.format(TASK_FORMAT, i + 1, task);
            foundTasks.add(taskString);
        }

        String userMessage;
        if (foundTasks.isEmpty()) {
            userMessage = "No tasks found!";
        } else {
            String tasksString = String.join("\n", foundTasks);
            userMessage = String.format(USER_MESSAGE_FORMAT, tasksString);
        }

        return new CommandResult(userMessage, false, false);
    }
}
