package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.exceptions.DukeException;

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
     * @param storage <code>Storage</code> that interacts with the local storage.
     * @throws DukeException If command fails to execute.
     */
    public abstract String execute(TaskList tasks, Storage storage) throws DukeException;
}
