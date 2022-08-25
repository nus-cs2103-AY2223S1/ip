package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;

public abstract class Command {

    private String info;

    public Command(String info) {
        this.info = info;
    }

    public String getInfo() {
        return this.info;
    }

    public abstract void execute(Ui ui, TaskList taskList) throws DukeException;
}
