package Commands;

import Duke.*;

public abstract class Command {
    boolean isExit = false;

    public abstract boolean isExit();

    public abstract void execute(Ui ui, Storage storage, TaskList taskList) throws DukeException;
}
