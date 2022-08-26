package duke.command;

import java.io.IOException;

import duke.exception.DukeException;
import duke.task.TaskList;
import duke.util.Storage;
import duke.util.Ui;

/**
 * Abstract class Command to represent the different types of commands user can input into the ChatBot.
 */
public abstract class Command {
    /**
     * Execution of the corresponding command.
     *
     * @param tasks a list that keeps track of the tasks added/removed
     * @param ui ui that handles the interaction with user inputs
     * @param storage storage that handles the writing/reading of data from a txt file
     * @throws DukeException if any duke specific error occurs
     * @throws IOException if an error occurs when writing/reading from a txt file
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException;

    /**
     * Check if it is the exit command in order to exit loop
     *
     * @return true if it is the 'bye' command, else returns false
     */
    public abstract boolean isExit();
}
