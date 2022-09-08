package duke.command;

import duke.DukeException;
import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;

import java.io.IOException;

/**
 * Command to archive current TaskList.
 */
public class ArchiveCommand extends Command {
    private static final String FILE_PATH = "data/tasks.txt";

    /**
     * Run the given command as an ArchiveCommand.
     *
     * @param taskList TaskList containing the list of tasks.
     * @param ui Ui dealing interaction with user.
     * @param storage Storage dealing with loading tasks from the save file and saving task in the save file.
     * @return String containing the message to user
     * @throws IOException If error is encountered saving onto the save file.
     * @throws DukeException If error is encountered when running the command.
     */
    public String execute(TaskList taskList, Ui ui, Storage storage) throws IOException, DukeException {
        storage.archive(FILE_PATH);
        taskList.clear();
        return ui.printArchive();
    }
}
