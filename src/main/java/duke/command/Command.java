package duke.command;

import java.io.IOException;

import duke.internal.Storage;
import duke.internal.Ui;
import duke.task.TaskList;

/**
 * An executable Duke command.
 */
public abstract class Command {
    /**
     * Returns whether Duke should exit after executing this command.
     *
     * @return true if the command is a terminal command, false otherwise.
     */
    public abstract boolean isTerminal();

    /**
     * Executes this command, potentially modifying the task list, storage, and UI.
     *
     * @param tasks the task list
     * @param storage the storage helper for the task list
     * @param ui the user interface
     * @throws IOException if an I/O error occurs while writing to the storage
     */
    public abstract void execute(TaskList tasks, Storage storage, Ui ui) throws IOException;
}
