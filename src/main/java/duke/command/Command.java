package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public abstract class Command {
    public abstract void execute(TaskList taskList, Storage storage, Ui ui) throws DukeException;

    public abstract boolean isExit();
}