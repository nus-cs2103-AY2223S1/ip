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

    static void setUi(Ui ui) {
        Command.ui = ui;
    }

    static void setTaskList(TaskList taskList) {
        Command.taskList = taskList;
    }

    static void setStorage(Storage storage) {
        Command.storage = storage;
    }

    boolean isExit() {
        return this.isExit;
    }

    abstract void execute() throws DukeException;
}
