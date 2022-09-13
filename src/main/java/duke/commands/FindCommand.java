package duke.commands;

import java.util.ArrayList;

import duke.exceptions.DukeException;
import duke.exceptions.WrongFindFormatException;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Represents a find command in the Duke application.
 */
public class FindCommand extends Command {
    /** Command word of the find command. */
    public static final String COMMAND_WORD = "find";
    private static final String TASK_FORMAT = "%d: %s";
    private static final String USER_MESSAGE_FORMAT = "*beeeeep* I've found some tasks!\n%s";
    private static final String NO_TASKS_FOUND = "I didn't find any tasks... *sad beep*";
    private final String keyword;

    /**
     * Constructs a delete command with arguments.
     *
     * @param arguments Arguments string is the find keyword.
     * @throws DukeException Exception due to invalid arguments.
     */
    public FindCommand(String arguments) throws DukeException {
        if (arguments.isEmpty()) {
            throw new WrongFindFormatException();
        }
        keyword = arguments;
    }

    private ArrayList<String> getFoundTasks() {
        ArrayList<String> foundTasks = new ArrayList<>();
        TaskList filteredTasks = tasks.filter(task -> task.containsKeyword(keyword));

        for (int i = 0; i < filteredTasks.size(); i++) {
            Task task = filteredTasks.getTask(i);
            String taskString = String.format(TASK_FORMAT, i + 1, task);
            foundTasks.add(taskString);
        }

        return foundTasks;
    }

    private String getUserMessage(ArrayList<String> foundTasks) {
        if (foundTasks.isEmpty()) {
            return NO_TASKS_FOUND;
        }
        String tasksString = String.join("\n", foundTasks);
        return String.format(USER_MESSAGE_FORMAT, tasksString);
    }

    @Override
    public CommandResult execute() {
        assert tasks != null : "Should setData() before calling execute().";
        ArrayList<String> foundTasks = getFoundTasks();
        String userMessage = getUserMessage(foundTasks);
        return new CommandResult(userMessage);
    }
}
