package duke.command;

import duke.util.Storage;
import duke.util.Ui;
import duke.exception.DukeException;
import duke.task.TaskList;

import java.io.IOException;

public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException;

    public abstract boolean isExit();
}
