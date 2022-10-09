package duke.command;

import java.io.IOException;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Executable command that modifies the list of tasks and/or storage.
 * CS2103T iP
 * AY22/23 Semester 1
 *
 * @author Perry Wong
 */
public abstract class Command {
    /**
     * Returns whether command is an ExitCommand.
     *
     * @return Whether the command will cause the Duke program to exit.
     */
    public abstract boolean isExit();

    /**
     * Executes the command.
     *
     * @param taskList List of tasks being operated on.
     * @param ui UI that prints corresponding responses.
     * @param storage Storage for saving purposes if applicable.
     * @throws DukeException If the input task or index is invalid.
     * @throws IOException If there is an issue with saving the list to Storage.
     */
    public abstract String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException, IOException;

}
