package duke.command;
import duke.command.Command;
import duke.exception.DukeException;
import duke.tasklist.TaskList;
import duke.utility.Storage;
import duke.utility.Ui;

/**
 * Represents command for mark keyword
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
     * Executes the mark command
     *
     * @param tasks The list containing all the tasks
     * @param ui User interface for printing the message
     * @param storage To write and read from a text file
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
