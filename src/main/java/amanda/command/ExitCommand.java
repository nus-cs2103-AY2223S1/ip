package main.java.amanda.command;

import main.java.amanda.manager.StoreManager;
import main.java.amanda.manager.TaskList;
import main.java.amanda.ui.Ui;

/**
 * ExitCommand is a command that print the exit message for the program.
 */
public class ExitCommand extends Command {

    /**
     * Constructor for ExitCommand class.
     */
    public ExitCommand() {
        super(null, 0);
    }

    /**
     * Call the ui to show goodbye message.
     * @param tasks the current task list.
     * @param ui the current Ui.
     * @param store the store manager that write any changes to the storage file.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, StoreManager store) {
        ui.showExitCommand(); // Print goodbye message.
    }
}
