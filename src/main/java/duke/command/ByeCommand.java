package duke.command;

import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Handles the actions of the Duke programme before
 * it is shut down.
 */
public class ByeCommand extends Command {

    /**
     * Initialises a ByeCommand to store the details of the
     * user's input and the TaskList.
     */
    public ByeCommand(String[] commandArgs, TaskList tasks) {
        super(commandArgs, tasks);
    }

    /**
     * Sends a goodbye message to the ui.
     */
    @Override
    public boolean performAction() {
        Ui.print("MumBot: Goodbyeeee sweetheart <3");
        return false;
    }
}

