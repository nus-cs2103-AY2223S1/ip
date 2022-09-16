package duke;

/**
 * Represents a Command to delete a Task from Duke.
 */
public class DeleteCommand extends Command {
    int index;

    /**
     * Constructor of DeleteCommand with index of Task to delete.
     *
     * @param index Index of Task to delete.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Run the DeleteCommand, delete a Task from Duke.
     *
     * @param duke Duke instance to run the Command at.
     */
    @Override
    public void run(Duke duke) {
        duke.deleteTask(index);
    }
}
