package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Command can be executed according to its type.
 *
 * @author Samsation
 * @version CS2103T AY 22/23 Sem 1
 *
 */

public abstract class Command {
    private boolean isExit;

    /**
     * A constructor for Command.
     * @param isExit Has the Command ended the session with Duke.
     */
    public Command(boolean isExit) {
        this.isExit = isExit;
    }

    /**
     * A method that checks if the Command ended the session with Duke.
     * @return Has the Command ended the session with Duke.
     */
    public boolean isExit() {
        return this.isExit;
    }

    /**
     * A method that executes the Command according to its type.
     *
     * @param tasks The TaskList containing the task list.
     * @param ui The Ui dealing with interactions with the user.
     * @param storage The Storage dealing with loading tasks from the file and saving tasks in the file.
     * @throws DukeException Depending on type of Command.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;
}
