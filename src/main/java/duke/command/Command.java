package duke.command;

import duke.DukeException;
import duke.Ui;
import duke.storage.Storage;
import duke.task.TaskList;

public abstract class Command {

    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException;

    public abstract boolean isExit();
}
