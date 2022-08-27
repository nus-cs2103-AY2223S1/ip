package maria.command;

import java.util.ArrayList;
import java.util.List;

import maria.TaskManager;
import maria.task.Task;

/**
 * Represents the command for finding all the tasks whose names contain a given string.
 */
public class CommandFindTask extends Command {

    private String searchString;

    public CommandFindTask(String searchString) {
        this.searchString = searchString;
    }

    /**
     * Executes the command.
     *
     * Finds all the tasks that contains the search string (case-sensitive) and add them to the task manager.
     *
     * @param taskManager The overall-in-charge for all task related affairs
     */
    @Override
    public void execute(TaskManager taskManager) {

        List<Task> filteredList = new ArrayList<>();

        for (Task t : taskManager.getTaskList()) {
            if (t.doesNameContainsString(this.searchString)) {
                filteredList.add(t);
            }
        }

        taskManager.setTaskListFindResult(filteredList);

    }

}
