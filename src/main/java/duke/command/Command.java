package duke.command;

import duke.*;

/**
 * Command runs certain commands input by user.
 */
public abstract class Command {
    boolean isExit;

    /**
     * Constructor for Command.
     */
    public Command() {
        this.isExit = false;
    }

    /**
     * Returns if command is an exit command.
     * @return boolean value whether command is exit command.
     */
    public boolean isExit() {
        return this.isExit;
    }

    /**
     * Executes command.
     * @param tasks TaskList containing tasks list.
     * @param ui Ui dealing with user interaction.
     * @param storage storage loading and saving tasks.
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws InvalidIndexException,
            InvalidDateException;
}
