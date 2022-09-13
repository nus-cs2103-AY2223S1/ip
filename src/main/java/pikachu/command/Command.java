package pikachu.command;

import pikachu.PikachuException;
import pikachu.Storage;
import pikachu.TaskList;
import pikachu.Ui;

/**
 * Represents abstract command. A <code>Command</code> object corresponds to
 * a command key in by user e.g., <code>unmark 2</code>.
 */
public abstract class Command {
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws PikachuException;

    public abstract boolean isExit();
}
