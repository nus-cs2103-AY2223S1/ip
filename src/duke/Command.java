package duke;

import duke.exceptions.DukeException;

public abstract class Command {

    private boolean isExit = false;

    public void toggleExit() {
        this.isExit = !isExit;
    }
    public Boolean isExit() {
        return this.isExit;
    }

    public void execute(Ui ui, TaskList taskList) throws DukeException {
    }
}
