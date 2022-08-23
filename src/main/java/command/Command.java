package command;

import exceptions.DukeException;
import tasklist.TaskList;
import ui.UI;
import storage.Storage;

public abstract class Command {
    public abstract void execute(TaskList taskList, UI ui, Storage storage) throws DukeException;


    public abstract boolean isExit();

}
