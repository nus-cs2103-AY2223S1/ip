package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;


/**
 * Class to encapsulate a command to search for a Task.
 */
public class FindCommand extends Command {
    private String keywords;

    /**
     * Constructor for a Find Command.
     *
     * @param keywords The keyword to search for in the list items.
     */
    public FindCommand(String keywords) {
        super();
        this.keywords = keywords;
    }

    /**
     * Searches and prints tasks with matching keyword.
     *
     * @param tasks The list of tasks to search.
     * @param ui The user interface.
     * @param storage The storage location of the list file in the system.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        int count = 1;
        String result = "Here are the matching tasks in your list:\n";
        for (int i = 0; i < tasks.getsize(); i++) {
            Task task = tasks.get(i);
            if (task.getDesc().contains(this.keywords)) {
                result += String.format("%d. %s\n", count, task);
                count++;
            }
        }
        if (count == 1) {
            return String.format("Oops, no tasks were found with: %s", keywords);
        }
        return result;
    }
}
