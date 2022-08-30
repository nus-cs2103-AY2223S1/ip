package command;
import tasklist.TaskList;
import utility.Storage;
import utility.Ui;

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
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        //create new task here
        String message = tasks.mark(index - 1);
        ui.mark(message);
        storage.update(tasks.getTasks());
    }
    
}
