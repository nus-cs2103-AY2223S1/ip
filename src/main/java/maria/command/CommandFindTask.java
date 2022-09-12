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
     * @return The display message for the execution
     */
    @Override
    public String execute(TaskManager taskManager) {

        List<Task> filteredList = new ArrayList<>();

        for (Task t : taskManager.getTaskList()) {
            if (t.doesNameContainsString(this.searchString)) {
                filteredList.add(t);
            }
        }

        // Converts the task list to a multiline string for display
        StringBuilder result = new StringBuilder("Your matching tasks are \n");

        for (int i = 0; i < filteredList.size(); ++i) {
            result.append("- ")
                    .append(filteredList.get(i).toString())
                    .append(i == filteredList.size() - 1 ? "" : "\n");
        }

        return result.toString();

    }

}
