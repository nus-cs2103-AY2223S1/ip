package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public abstract class Command {

    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    public boolean isArchive() {
        return false;
    }
    public boolean isExit() {
        return false;
    }

    public void setArchiveTasks(TaskList archiveTasks) {
        //do nothing
    }

    public void setArchiveStorage(Storage archiveStorage) {
        //do nothing
    }

}
