package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public interface Command {
    void execute(Ui ui, Storage storage, TaskList taskList) throws DukeException;
}
