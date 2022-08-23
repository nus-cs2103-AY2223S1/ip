package duke.command;

import duke.DukeException;
import duke.task.TaskList;
import duke.util.Ui;

public abstract class Command {
    protected static Ui ui;
    protected static TaskList taskList;
    protected boolean isExit = false;

    public static void setUi(Ui ui) {
        Command.ui = ui;
    }

    public static void setTaskList(TaskList taskList) {
        Command.taskList = taskList;
    }

    public abstract void execute() throws DukeException;

    public boolean isExit() {
        return this.isExit;
    };
}
