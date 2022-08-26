package duke.commands;

import duke.exceptions.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Represents an executable <code>Command</code>.
 */
public abstract class Command {

    protected String description;

    /**
     * Constructs a <code>Command</code> object.
     *
     * @param description Input from the user.
     */
    public Command(String description) {
        this.description = description;
    }

    /**
     * Executes the command.
     *
     * @param tasks <code>TaskList</code> to be interacted with this command.
     * @param ui <code>Ui</code> to print messages after the command executes.
     * @param storage <code>Storage</code> that interacts with the local storage.
     * @throws DukeException If command fails to execute.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    /**
     * Returns whether the command is the exit command.
     *
     * @return True if and only if the <code>Command</code> is the exit command.
     */
    public boolean isExit() {
        return false;
    }
}
