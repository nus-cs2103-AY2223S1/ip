package duke.command;

import duke.TaskList;
import duke.Ui;

public abstract class Command {
    private boolean isExit = false;

    public abstract void execute(TaskList taskList, Ui ui);

    public void setExit() {
        this.isExit = true;
    }

    public boolean getExit() {
        return this.isExit;
    }
}
