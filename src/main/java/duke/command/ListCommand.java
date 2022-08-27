package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.Task;
import duke.TaskList;
import duke.Ui;

public class ListCommand extends Command{

    /**
     * Lists the tasks that the user has.
     * @param tasks List of tasks.
     * @param ui User interface for duke.
     * @param storage Storage information for tasks.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException{
        if (tasks.getCount() == 0) {
            throw new DukeException("You currently have no tasks remaining! Create a task now!");
        }
        for (int i = 0; i < tasks.getCount(); i++) {
            if (tasks.get(i) == null) {
                break;
            }
            else {
                System.out.println((i+1) + ". " + tasks.get(i).toString());
            }
        }
    }


}
