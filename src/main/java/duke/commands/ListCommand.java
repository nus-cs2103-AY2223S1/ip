package duke.commands;

import duke.exception.DukeException;
import duke.main.Storage;
import duke.main.Ui;
import duke.tasks.TaskList;

/**
 * ListCommand lists all tasks in TaskList
 */
public class ListCommand extends Command {

    /**
     * Lists all tasks in tasklist or print default message if no tasks found
     *
     * @return @inheritDoc
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (tasks.size() == 0) {
            return "No task found so far.";
        } else {
            StringBuilder sb = new StringBuilder("Here are the tasks in your list: \n");
            for (int i = 0; i < tasks.size(); i++) {
                sb.append((i + 1) + ". " + tasks.get(i).toString() + '\n');
            }
            return sb.toString();
        }

    }
}
