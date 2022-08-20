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
    protected boolean isExit;

    /**
     * Constructor for Command.
     */
    public Command() {
        this.isExit = false;
    }

    /**
     * Checks if the Command causes the program to exit.
     *
     * @return Whether the Command causes the program to exit.
     */
    public boolean isExit() {
        return this.isExit;
    }

    /**
     * Executes the Command according to its type and details.
     *
     * @param tasks TaskList containing the Task list.
     * @param ui Ui handling interactions with the user.
     * @param storage Storage handling loading data from and saving data to files.
     * @throws RuntimeException If the user provides an invalid input.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws RuntimeException;
}
