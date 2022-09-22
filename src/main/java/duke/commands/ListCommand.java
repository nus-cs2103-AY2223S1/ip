package duke.commands;

import duke.exception.DukeException;
import duke.main.Storage;
import duke.tasks.TaskList;

/**
 * ListCommand lists all tasks in TaskList
 */
public class ListCommand extends Command {

    /**
     * Lists all tasks in TaskList or print default message if no tasks found
     *
     * @return @inheritDoc
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        return getMessage(tasks);
    }

    public String getMessage(TaskList tasks) {
        if (tasks.size() == 0) {
            return "Your excellency, no task found so far.";
        } else {
            StringBuilder sb = new StringBuilder("Your excellency, here are the tasks in your list: \n");
            for (int i = 0; i < tasks.size(); i++) {
                sb.append(i + 1).append(". ").append(tasks.get(i).toString()).append('\n');
            }
            return sb.toString();
        }
    }
}
