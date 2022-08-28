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
    /**
     * A method that executes the Command according to its type.
     *
     * @param tasks The TaskList containing the task list.
     * @param ui The Ui dealing with interactions with the user.
     * @param storage The Storage dealing with loading tasks from the file and saving tasks in the file.
     * @return A response fron Duke.
     * @throws DukeException Depending on type of Command.
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;
}
