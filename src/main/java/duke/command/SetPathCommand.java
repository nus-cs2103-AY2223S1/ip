package duke.command;

import java.io.IOException;

import duke.DukeException;
import duke.TaskList;
import duke.Ui;
import duke.storage.Storage;

/**
 * Can be executed to change the file path for local storage.
 */
public class SetPathCommand extends Command {

    private final String filePath;

    public SetPathCommand(String path) {
        filePath = path;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            storage.setFilePath(filePath);
            storage.writeToFile(taskList.list());
            ui.display("Changed filepath. Be careful, if a file did not already "
                    + "exist at that location, a new file would be created");
        } catch (IOException e) {
            throw new DukeException("Check if the specified filepath exists");
        }
    }

    @Override
    public String execute(TaskList taskList, Storage storage) {
        try {
            storage.setFilePath(filePath);
            storage.writeToFile(taskList.list());
            return String.format("File path change successfully to %s", filePath);
        } catch (IOException e) {
            throw new DukeException("Check if the specified filepath exists");
        }
    }

}
