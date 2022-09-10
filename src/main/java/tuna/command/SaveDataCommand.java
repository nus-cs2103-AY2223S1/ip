package tuna.command;

import tuna.TunaException;
import tuna.utility.Storage;
import tuna.utility.TaskList;
import tuna.utility.Ui;

/**
 * Represents a save data command. A SaveDataCommand object contains the file path of the data file.
 */
public class SaveDataCommand extends Command {

    /** File path of the data file */
    private String filePath;

    /**
     * Creates a SaveDataCommand object.
     *
     * @param filePath file path of the data file.
     */
    public SaveDataCommand(String filePath) {
        super(CommandType.SAVE);
        assert !filePath.equals("");
        this.filePath = filePath;
    }

    /**
     * Executes the save data command, saving the tasks list data into the file specified by the file path.
     *
     * @param tasks TaskList object.
     * @param ui Ui object.
     * @param storage Storage object.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws TunaException {
        storage.saveFileContents(filePath, tasks);
        return ui.fileSavedMessage();
    }
}

