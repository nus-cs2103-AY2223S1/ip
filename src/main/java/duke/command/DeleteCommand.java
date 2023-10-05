package duke.command;

import duke.exception.DukeException;
import duke.tasklist.TaskList;
import duke.utility.Storage;
import duke.utility.Ui;

/**
 * Represents command for delete keyword which removes a task
 * at a specified index
 */
public class DeleteCommand extends Command {
    private int index;

    /**
     * Instantiates a new delete command
     */
    public DeleteCommand(int index) {
        super("delete");
        this.index = index;
    }

    /**
     * Executes the delete command which removes a task
     * at a specified index
     *
     * @param tasks The list containing all the tasks
     * @param ui User interface for printing the message
     * @param storage To write and read from a text file
     * @return Returns String that contains message to be printed by gui
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            String message = tasks.deleteTask(index - 1);
            String output = ui.delete(tasks.numOfTasks(), message);
            storage.update(tasks.getTasks());
            return output;
        } catch (DukeException e) {
            String output = ui.showLoadingError(e.getMessage());
            return output;
        }
    }
    
}
