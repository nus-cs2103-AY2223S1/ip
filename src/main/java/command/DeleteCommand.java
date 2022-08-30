package command;

import exception.DukeException;
import tasklist.TaskList;
import utility.Storage;
import utility.Ui;

/**
 * Represents command for delete keyword
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
     * Executes the delete command
     *
     * @param tasks The list containing all the tasks
     * @param ui User interface for printing the message
     * @param storage To write and read from a text file
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        //create new task here
        try {
            String message = tasks.deleteTask(index - 1);
            ui.delete(tasks.numOfTasks(), message);
            storage.update(tasks.getTasks());
        } catch (DukeException e) {
            ui.showLoadingError(e.getMessage());
        }
    }
    
}
