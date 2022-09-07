package duke.command;

import duke.InvalidDateException;
import duke.InvalidIndexException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Command runs certain commands input by user.
 */
public abstract class Command {
    private boolean isExit;

    /**
     * Constructor for Command.
     */
    public Command() {
        this.isExit = false;
    }

    public void setExit(boolean isExit) {
        this.isExit = isExit;
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
