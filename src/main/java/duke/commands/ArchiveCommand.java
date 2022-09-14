package duke.commands;

import duke.storage.Storage;
import duke.task.TaskList;

import static duke.ui.Messages.MESSAGE_ARCHIVE_SUCCESSFULLY;

public class ArchiveCommand extends Command {
    private final Storage backupDataFile = new Storage("duke-backup.txt");

    public String execute(TaskList taskList) {
        backupDataFile.appendSave(taskList);
        taskList.clear();
        return MESSAGE_ARCHIVE_SUCCESSFULLY;
    }
}
