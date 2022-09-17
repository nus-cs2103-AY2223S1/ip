package main.java.command;

import main.java.exception.DukeException;
import main.java.main.Storage;
import main.java.main.TaskList;
import main.java.main.Ui;
import main.java.task.Task;

public abstract class Command {

    Command() {
    }

    public boolean isEnd() {
        return false;
    }

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException; //Referenced from Marcus Ong Wee's code

    public abstract Task getTask() throws DukeException;

}
