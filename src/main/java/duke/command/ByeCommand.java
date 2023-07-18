package duke.command;

import java.io.IOException;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * ByeCommand is a Command that exits the chatbot program.
 *
 * @author John Russell Himawan
 * @version CS2103T AY22/23 Sem 1
 */

public class ByeCommand extends Command {

    /**
     * Outputs the goodbye message and terminates the program.
     *
     * @param tasks A TaskList containing the Tasks.
     * @param ui The Ui which handles interactions with the user.
     * @param storage The Storage which handles loading and saving data from the file.
     * @throws DukeException
     * @throws IOException
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        storage.save(tasks);
        return ui.displayBye();
    }

    /**
     * Queries whether the program should be exited.
     *
     * @return The boolean representing whether should exit program.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
