package duke.command;

import duke.exception.DukeException;
import duke.tasklist.TaskList;
import duke.utility.Storage;
import duke.utility.Ui;

/**
 * Represents command for Unmark keyword that will
 * unmark a task to not completed at a specified index
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
     * Executes the Unmark command that will
     * unmark a task to not completed at a specified index
     *
     * @param tasks The list containing all the tasks
     * @param ui User interface for printing the message
     * @param storage To write and read from a text file
     * @return Returns String that contains task that is deleted to be printed by gui
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            String message = tasks.unmark(index - 1);
            String output = ui.unmark(message);
            storage.update(tasks.getTasks());
            return output;
        } catch (DukeException e) {
            String output = ui.showLoadingError(e.getMessage());
            return output;
        }

    }
}
