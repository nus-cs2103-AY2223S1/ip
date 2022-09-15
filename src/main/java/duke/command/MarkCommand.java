package duke.command;


import duke.exception.DukeException;
import duke.tasklist.TaskList;
import duke.utility.Storage;
import duke.utility.Ui;

/**
 * Represents command for mark keyword that will
 * mark a task as completed at a specified index
 */
public class MarkCommand extends Command {

    private int index;

    /**
     * Instantiates a new mark command
     */
    public MarkCommand(int index) {
        super("mark");
        this.index = index;
    }

    /**
     * Executes the mark command will
     * mark a task as completed at a specified index
     *
     * @param tasks The list containing all the tasks
     * @param ui User interface for printing the message
     * @param storage To write and read from a text file
     * @return Returns String that contains message to be printed by gui
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        //create new task here
        try {
            String message = tasks.mark(index - 1);
            String output = ui.mark(message);
            storage.update(tasks.getTasks());
            return output;
        } catch (DukeException e) {
            String output = ui.showLoadingError(e.getMessage());
            return output;
        }
    }
    
}
