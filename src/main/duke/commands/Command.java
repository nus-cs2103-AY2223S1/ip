package commands;

import others.DukeException;
import storage.Storage;
import storage.TaskList;
import ui.Ui;

public abstract class Command {

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        throw new DukeException("This method is to be implemented by child classes.");
    }

    public abstract boolean isRunning();

}
