package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.ui.Ui;
import duke.util.TaskList;

public abstract class Command {
    public abstract void execute(Storage storage, TaskList tasks, Ui ui) throws DukeException;
}