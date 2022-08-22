package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

import java.io.IOException;

public abstract class Command {
    private boolean isEnd = false;

    public abstract void run(TaskList tasks, Ui ui, Storage storage) throws IOException;

    public void endApp() {
        this.isEnd = true;
    }

    public boolean getIsEnd() {
        return this.isEnd;
    }
}
