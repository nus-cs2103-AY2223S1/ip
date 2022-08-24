package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.DukeException;
import duke.Ui;

public abstract class Command {

    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException;

    public abstract boolean isExit();
}
