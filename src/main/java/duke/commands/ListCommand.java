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
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage)
            throws DukeException {
        if (tasks.size() == 0) {
            System.out.println("No task found so far.");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + ". " + tasks.get(i).toString());
            }
        }
    }
}
