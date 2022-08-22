package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;

public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    public abstract String getCommand();
}
