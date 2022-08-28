package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Command class for various commands.
 */
public abstract class Command {
    /**
     * Default constructor for Command.
     */
    public Command() {
    }

    /**
     * Executes a command.
     *
     * @param tasks TaskList that stores Tasks objects.
     * @param ui Ui that handles user interaction.
     * @param storage Storage that handles storing information on memory files.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage);

    /**
     * Checks if program should exit.
     *
     * @return A boolean indicating if program should exit.
     */
    public abstract boolean isExit();
}
