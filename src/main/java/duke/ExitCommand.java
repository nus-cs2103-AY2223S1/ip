/**
 * This class handles all commands related to exiting the program
 * and inherits from the Command class.
 */
package duke;

public class ExitCommand extends Command {
    /** Flag to indicate if program has been exited */
    private boolean isExit;

    /**
     * Constructor for the ExitCommand class.
     */
    public ExitCommand() {
        this.isExit = false;
    }

    /**
     * {@inheritDoc}
     * Exits from the program without modifying the tasklist.
     */
    @Override
    public TaskList execute(TaskList taskList, Ui ui, Storage storage) {
        this.isExit = true;
        ui.showExit();
        return taskList;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isExit() {
        return this.isExit;
    }
}
