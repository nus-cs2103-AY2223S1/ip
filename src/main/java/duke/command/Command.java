package duke.command;

import duke.data.Storage;
import duke.data.TaskList;
import duke.util.DukeException;
import duke.util.Ui;

/**
 * Command Class to execute user input.
 * @author Jicson Toh
 */
public abstract class Command {

    /**
     * Executes the command input.
     * @param tasks amends task list if any.
     * @param ui ui to output feedback.
     * @param storage make changes to storage if any.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    /**
     * Returns true if exiting program.
     * @return false if still running.
     */
    public abstract boolean isExit();
}
