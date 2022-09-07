package duke.commands;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.TextUi;

public abstract class Command {
    public abstract void execute(TaskList tasks, TextUi ui, Storage storage);

    public abstract boolean isExit();
}
