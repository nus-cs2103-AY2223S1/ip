package command;

import exception.DukeException;
import main.Storage;
import main.TaskList;
import main.Ui;
import task.Task;

public abstract class Command {

    Command() {
    }

    public boolean isEnd() {
        return false;
    }

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException; //Referenced from Marcus Ong Wee's code

    public abstract Task getTask() throws DukeException;

}
