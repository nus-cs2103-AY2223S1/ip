package duke.commands;

import java.util.ArrayList;

import duke.exceptions.DukeException;
import duke.task.Task;

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

    private void addToFoundTasksIfMatch(ArrayList<String> foundTasks, int index, Task task) {
        if (task.containsKeyword(keyword)) {
            String taskString = String.format(TASK_FORMAT, index + 1, task);
            foundTasks.add(taskString);
        }
    }

    private ArrayList<String> getFoundTasks() {
        ArrayList<String> foundTasks = new ArrayList<>();

        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.getTask(i);
            addToFoundTasksIfMatch(foundTasks, i, task);
        }

        return foundTasks;
    }

    private String getUserMessage(ArrayList<String> foundTasks) {
        String userMessage;
        if (foundTasks.isEmpty()) {
            userMessage = "No tasks found!";
        } else {
            String tasksString = String.join("\n", foundTasks);
            userMessage = String.format(USER_MESSAGE_FORMAT, tasksString);
        }
        return userMessage;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CommandResult execute() {
        assert tasks != null : "Should setData() before calling execute().";
        ArrayList<String> foundTasks = getFoundTasks();
        String userMessage = getUserMessage(foundTasks);
        return new CommandResult(userMessage, false, false);
    }
}
