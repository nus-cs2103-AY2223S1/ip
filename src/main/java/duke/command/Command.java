package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Command is a command that can be executed according to its type and details.
 *
 * @author Jet Lee
 * @version CS2103T AY22/23 Sem 1
 */
public abstract class Command {
    /**
     * Returns the response from Duke after executing the Command according to its type and details.
     *
     * @param tasks tasks TaskList containing the Task list.
     * @param ui Ui handling interactions with the user.
     * @param storage Storage handling loading data from and saving data to files.
     * @return The response from Duke.
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage);
}
