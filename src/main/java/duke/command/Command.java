package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

import java.io.IOException;

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
