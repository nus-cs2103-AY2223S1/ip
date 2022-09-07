package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;
import duke.note.NoteList;

/**
 * Represents a command to interact with Duke.
 */
public abstract class Command {
    protected boolean isExit;

    /**
     * Constructor for a command.
     */
    Command() {
        isExit = false;
    }

    /**
     * Executes the command.
     *
     * @param tasks the current task list containing all tasks
     * @param ui the user interface object
     * @param storage the storage objecct
     * @return the response to the command
     * @throws DukeException
     */
    public String execute(TaskList tasks, NoteList notes, Ui ui, Storage storage) throws DukeException {
        throw new DukeException("Not implemented");
    }

    /**
     * Returns true if the command is an exit command.
     *
     * @return
     */
    public boolean isExit() {
        return isExit;
    }
}
