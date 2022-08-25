package duke.command;

import duke.exception.DukeException;
import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;

public class Command {
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
    }

    public boolean exitBot() {
        return false;
    }
}
