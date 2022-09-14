package duke.command;

import duke.Storage;
import duke.TaskList;

/**
 * Encapsulation of the command of listing out the tasks in the list.
 *
 * @author Sun Ruoxin
 */
public class ListCommand extends Command {
    /**
     * Executes the command.
     * Show the feedback to the user.
     *
     * @param tasks the list of tasks
     * @param storage the storage
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        if (tasks.size() == 0) {
            return "You don't have any tasks right now.";
        } else {
            StringBuilder result = new StringBuilder("Here are the tasks in your list:\n");
            for (int i = 0; i < tasks.size(); i++) {
                result.append(i + 1).append(". ").append(tasks.get(i).toString()).append("\n");
            }
            return result.toString();
        }
    }
}
