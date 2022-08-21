package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class LoadDataCommand extends Command {

    private String folderPath;
    private String filePath;

    public LoadDataCommand(String folderPath, String filePath) {
        super(CommandType.LOAD);
        this.folderPath = folderPath;
        this.filePath = filePath;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        storage.loadFileContents(folderPath, filePath, tasks);
    }
}
