package tuna.command;

import tuna.TunaException;
import tuna.utility.Storage;
import tuna.utility.TaskList;
import tuna.utility.Ui;

/**
 * Represents a load data command. A LoadDataCommand contains the folder path and file path.
 */
public class LoadDataCommand extends Command {

    /** Folder path of the data file */
    private String folderPath;
    /** File path of the data file */
    private String filePath;

    /**
     * Creates a LoadDataCommand object.
     *
     * @param folderPath folder path of the data file.
     * @param filePath file path of the data file.
     */
    public LoadDataCommand(String folderPath, String filePath) {
        super(CommandType.LOAD);
        assert !folderPath.equals("");
        assert !filePath.equals("");
        this.folderPath = folderPath;
        this.filePath = filePath;
    }

    /**
     * Executes the load data command, loading the data from the file path provided into the tasks list.
     *
     * @param tasks TaskList object.
     * @param ui Ui object.
     * @param storage Storage object.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws TunaException {
        storage.loadFileContents(folderPath, filePath, tasks);
        return ui.fileLoadedMessage();
    }
}
