package command;

import tasklist.TaskList;
import utility.Storage;
import utility.Ui;

/**
 * Represents command for Unmark keyword
 */
public class UnmarkCommand extends Command {
    private int index;

    /**
     * Instantiates a new Unmark command
     */
    public UnmarkCommand(int index) {
        super("unmark");
        this.index = index;
    }

    /**
     * Executes the Unmark command
     *
     * @param tasks The list containing all the tasks
     * @param ui User interface for printing the message
     * @param storage To write and read from a text file
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        //create new task here
        String message = tasks.unmark(index - 1);
        ui.unmark(message);
        storage.update(tasks.getTasks());
    }
}
