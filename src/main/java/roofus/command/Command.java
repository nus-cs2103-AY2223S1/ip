package roofus.command;

import roofus.Storage;
import roofus.TaskList;
import roofus.Ui;

public abstract class Command {
    public abstract void execute(TaskList taskList,
                                 Storage storage, Ui ui);
    public abstract boolean isRunning();
}
