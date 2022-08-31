package duke.command;

import duke.manager.Storage;
import duke.manager.TaskList;
import duke.task.Task;

import java.util.ArrayList;

/**
 * Represents the command to find matching tasks from the list.
 */
public class FindCommand extends Command {
    /** The keyword to search the list of tasks for. */
    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public String execute(TaskList tasks, Storage storage) {
        ArrayList<Task> foundTasks = tasks.findTask(this.keyword);

        String matchingTasks = "";

        for (Task task : foundTasks) {
            matchingTasks += "\n" + task;
        }

        String response = "Here are the matching tasks in your list:" + matchingTasks;

        return response;
    }
}
