package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

public class Command {
    protected Task task;
    protected int index;
    protected boolean isExit = false;

    Command(int index) {
        this.index = index;
    }

    Command(Task task) {
        this.task = task;
    }

    Command() {

    }

    public boolean isExit() {
        return this.isExit;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {

    }
}
