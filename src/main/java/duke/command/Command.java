package duke.command;

import java.io.IOException;

import duke.exception.DukeException;
import duke.task.TaskList;
import duke.util.Storage;
import duke.util.Ui;

/**
 * Represents the different types of command user can input into the ChatBot.
 *
 * @author bensohh
 * @version CS2103T AY 22/23 Sem 1 (G01)
 */
public abstract class Command {
    /**
     * Executes the corresponding command.
     *
     * @param tasks List that keeps track of the tasks added/removed
     * @param ui Ui that handles the interaction with user inputs
     * @param storage Storage that handles the writing/reading of data from a txt file
     * @return String that represents the response from ChatBot after executing the command
     * @throws DukeException if any duke specific error occurs
     * @throws IOException if an error occurs when writing/reading from a txt file
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException;
}
