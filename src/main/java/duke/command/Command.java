package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public abstract class Command {
    protected static Ui ui;
    protected static TaskList taskList;
    protected static Storage storage;
    protected boolean isExit;

    public static void setUi(Ui ui) {
        Command.ui = ui;
    }

    public static void setTaskList(TaskList taskList) {
        Command.taskList = taskList;
    }

    public static void setStorage(Storage storage) {
        Command.storage = storage;
    }

    public boolean isExit() {
        return this.isExit;
    }

    public abstract void execute() throws DukeException;
}
