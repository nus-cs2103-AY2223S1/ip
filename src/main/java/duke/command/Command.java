package duke.command;

import duke.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.Ui;

public abstract class Command {

    public boolean isDone() {
        return false;
    }

    public void handle(Storage storage, Ui ui, TaskList taskList) throws DukeException {};
}
