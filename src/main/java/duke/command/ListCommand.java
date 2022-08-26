package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * The ListCommand is a command that lists all the tasks
 *
 * @author Eugene Tan
 */
public class ListCommand extends Command {

    public ListCommand() {
        super();
    }

    /**
     * Prints out all the task in the TaskList
     *
     * @param tasks Tasklist containing the tasks
     * @param ui Ui handling the user interaction
     * @param storage Storage to store data
     */
    @Override
    public void run(TaskList tasks, Ui ui, Storage storage) {
        ui.printAnyOtherMessage(tasks.toString());
    }
}
