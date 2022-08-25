package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

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
    public abstract void execute(TaskList tasks, Ui ui, Storage storage);
}
