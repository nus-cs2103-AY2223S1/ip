package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class SaveDataCommand extends Command {
    private String filePath;

    public SaveDataCommand(String filePath) {
        super(CommandType.SAVE);
        this.filePath = filePath;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        storage.saveFileContents(filePath, tasks);
    }
}

