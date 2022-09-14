package duke.commands;

import duke.Storage;
import duke.DukeException;
import duke.Ui;
import duke.tasks.TaskList;

public abstract class Command {
    public String instruction;

    public Command(String instruction) {
        this.instruction = instruction;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        throw new DukeException("Should not run");
    }

    public boolean isExit() {
        return false;
    }
}
