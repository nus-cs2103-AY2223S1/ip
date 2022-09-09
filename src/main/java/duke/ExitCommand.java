package duke;

/**
 * This class handles all commands related to exiting the program
 * and inherits from the Command class.
 */
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
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        this.isExit = true;
        return ui.showExit();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isExit() {
        return this.isExit;
    }
}
