package duke.commands;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import duke.exceptions.DukeException;
import duke.managers.TaskManager;
import duke.models.task.Task;

/**
 * Encapsulates a command for finding a {@link Task}. The command should be used as follows:
 * <ul>
 *     <li>
 *         {@code find {taskKeyword}}: To find tasks whose description contains the corresponding keyword.
 *     </li>
 * </ul>
 *
 * @author Emily Ong Hui Qi
 */
public class FindTaskCommand implements Command {
    public static final String COMMAND_WORD = "find";
    public static final String COMMAND_DESCRIPTION = FindTaskCommand.COMMAND_WORD
            + " <keyword1> <keyword2> ...: Finds the tasks from the list containing all provided keywords";

    private static final String ERROR_NO_SEARCH_KEYWORD = "Provide a search keyword to find the task!\n"
            + "For example, use `find book mystery` to find all tasks where it's\n"
            + "description contains the word 'book' and 'mystery'";

    private static final String MESSAGE_MATCHING_TASKS_FOUND = "Here are the matching tasks in your list:";
    private static final String MESSAGE_NO_MATCHING_TASKS_FOUND = "No matching tasks are found in your list!";

    private final String[] arguments;

    /**
     * Creates a new instance of the Command handler for finding a {@link Task}.
     *
     * @param arguments The arguments following the {@code 'find'} prefix supplied by the user from keyboard input
     */
    public FindTaskCommand(String ...arguments) {
        this.arguments = arguments;
    }

    @Override
    public String execute(TaskManager taskManager) throws DukeException {
        if (this.arguments.length == 0) {
            throw new DukeException(FindTaskCommand.ERROR_NO_SEARCH_KEYWORD);
        }

        Predicate<Task> areKeywordsInTaskDescription = task -> (
                Arrays.stream(this.arguments).allMatch(keyword -> task.getDescription().contains(keyword))
        );

        List<Task> filteredTasks = taskManager.list(areKeywordsInTaskDescription);

        if (filteredTasks.size() == 0) {
            return FindTaskCommand.MESSAGE_NO_MATCHING_TASKS_FOUND;
        } else {
            return String.format(
                    "%s\n%s",
                    FindTaskCommand.MESSAGE_MATCHING_TASKS_FOUND,
                    TaskManager.display(filteredTasks)
            );
        }
    }
}
