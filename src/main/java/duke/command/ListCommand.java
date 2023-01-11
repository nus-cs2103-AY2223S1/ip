package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * ListCommand lists the tasks currently present in task list.
 */
public class ListCommand extends Command{

    /**
     * Lists the tasks that the user has.
     *
     * @param tasks List of tasks.
     * @param ui User interface for duke.
     * @param storage Storage information for tasks.
     * @return String output to be displayed by duke.
     * @throws DukeException
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException{
        if (tasks.getCount() == 0) {
            throw new DukeException("You currently have no tasks remaining! Create a task now!");
        }

        StringBuilder toReturn = new StringBuilder();
        for (int i = 0; i < tasks.getCount(); i++) {
            if (tasks.get(i) == null) {
                break;
            }
            else {
                toReturn.append((i+1) + ". " + tasks.get(i).toString() + "\n");
            }
        }
        return toReturn.toString();
    }


}
