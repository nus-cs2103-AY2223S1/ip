package duke.commands;

import duke.exceptions.DukeException;
import duke.managers.TaskManager;
import duke.managers.UiManager;
import duke.models.task.Task;

import java.util.List;

public class FindTaskCommand implements Command {
    private final String arguments;

    public static final String COMMAND_WORD = "find";

    private static final String ERROR_NO_SEARCH_KEYWORD = "Provide a search keyword to find the task!\n" +
            "For example, use `find book` to find all tasks where it's\n" +
            "description contains the word 'book'";

    private static final String MESSAGE_MATCHING_TASKS_FOUND = "Here are the matching tasks in your list:";
    private static final String MESSAGE_NO_MATCHING_TASKS_FOUND = "No matching tasks are found in your list!";

    public FindTaskCommand(String arguments) {
        this.arguments = arguments;
    }

    @Override
    public void execute(TaskManager taskManager, UiManager uiManager) throws DukeException {
        if (this.arguments.length() == 0) {
            throw new DukeException(FindTaskCommand.ERROR_NO_SEARCH_KEYWORD);
        }

        String keyword = this.arguments;
        List<Task> filteredTasks = taskManager.list(task -> task.getDescription().contains(keyword));
        if (filteredTasks.size() == 0) {
            uiManager.print(FindTaskCommand.MESSAGE_NO_MATCHING_TASKS_FOUND);
        } else {
            uiManager.print(
                    String.format(
                            "%s\n%s",
                            FindTaskCommand.MESSAGE_MATCHING_TASKS_FOUND,
                            TaskManager.display(filteredTasks)
                    )
            );
        }
    }
}
