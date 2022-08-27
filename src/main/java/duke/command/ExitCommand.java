package duke.command;

import java.io.IOException;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Command that exits the Duke program.
 * CS2103T iP
 * AY22/23 Semester 1
 *
 * @author Perry Wong
 */
public class ExitCommand extends Command {

    /**
     * Returns whether command is an ExitCommand.
     *
     * @return Whether the command will cause the Duke program to exit.
     */
    @Override
    public boolean isExit() {
        return true;
    }

    /**
     * Executes the command by exiting the Duke program.
     *
     * @param taskList List of tasks being operated on.
     * @param ui UI that prints corresponding responses.
     * @param storage Storage for saving purposes if applicable.
     * @throws IOException If there is an issue saving the list to Storage.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws IOException {
        storage.save(taskList.getTaskArrayList());
        ui.showGoodbye();
    }

}
