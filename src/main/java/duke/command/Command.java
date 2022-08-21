package duke.command;

import duke.common.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public abstract class Command {

  public abstract void execute(TaskList taskList, Ui ui, Storage storage)
    throws DukeException;

  public abstract boolean isExit();
}
