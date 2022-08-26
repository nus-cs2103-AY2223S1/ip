package DukeUI.Command;

import DukeUI.TaskList;
import DukeUI.Ui;
import DukeUI.FileStorage.Storage;

public abstract class Command {
    public boolean isExit() {
        return false;
    } 
    public abstract void execute(TaskList tasks, Ui ui, Storage storage);
}
