package duke.command;

import duke.Storage;
import duke.tasklist.TaskList;

/**
 * Concrete class representing LIST
 */
public class ListCommand extends Command {
    @Override
    public String execute(Storage storage, TaskList taskList) {
        String tasks = taskList.getTextRepresentationOfAllTasksForDisplay();
        return tasks.length() > 0 ? "Here are the tasks in your list:"
                + tasks : "No existing tasks found :(";
    }
}
