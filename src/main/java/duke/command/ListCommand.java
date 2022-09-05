package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Encapsulates a command to list all Tasks stored by Duke.
 */
public class ListCommand extends Command {
    /**
     * {@inheritDoc}
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        int len = tasks.getLength();
        ui.showOutput("You currently have " + len + " tasks:");
        ui.showOutput(tasks.toString());
    }

    /**
     * {@inheritDoc}
     */
    public boolean isExit() {
        return false;
    }
}
