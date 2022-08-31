package duke.command;

import duke.Storage;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Represents a command to search the specified task list for tasks containing
 * that specified keyword
 */
public class FindTaskCommand extends Command {
    private String keyword;

    /**
     * Constructs a FindTaskCommand
     *
     * @param keyword Search query to filter task list against
     */
    public FindTaskCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes a FindTaskCommand by filtering the task list for the keyword and displaying the result.
     *
     * @param tasks The task list to search in
     * @param storage The storage manager that deals with loading and saving tasks to the hard disk
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        TaskList filtered = tasks.filter((Task t) -> t.toString().contains(this.keyword));
        String filteredTasks = filtered.stringify();

        if (filteredTasks.equals("")) {
            return "Duke can't find any matching tasks in your list.";
        } else {
            return ("Here are the matching tasks in your list:\n" + filteredTasks);
        }
    }
}
