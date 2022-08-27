package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.ui.Ui;
import duke.util.TaskList;

public abstract class Command {
    /**
     * Executes the command.
     * This is the main way for outputting bot replies.
     *
     * @param storage the storage object
     * @param tasklist the tasklist object
     * @param ui the ui object
     * @throws DukeException if the user input is unrecognised
     */
    public abstract void execute(Storage storage, TaskList tasklist, Ui ui) throws DukeException;
}