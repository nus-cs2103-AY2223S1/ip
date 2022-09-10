package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;

public abstract class Command {
    public String response = "";

    public abstract void execute(TaskList taskList, Ui ui) throws DukeException;

    public String response() {
        return this.response;
    }

    public boolean isExit() {
        return false;
    }
}
