package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Abstract class that is the parent class of all commands.
 */
public abstract class Command {

    /**
     * Executes the specific command corresponding to the type of input the user gives.
     *
     * @param list List of tasks.
     * @param ui Ui to print messages.
     * @param storage To save the list after making changes.
     * @return String that matches the command input.
     */
    public abstract String execCommand(TaskList list, Ui ui, Storage storage);
}
