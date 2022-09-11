package command;

import java.io.IOException;

import exception.DukeIOException;
import exception.InvalidCommandException;
import exception.InvalidDateException;
import exception.MissingArgumentException;
import main.Storage;
import main.TaskList;
import main.Ui;
import task.Task;

public abstract class Command {

    protected String commandArgs;

    Command(String commandArgs) {
        this.commandArgs = commandArgs;
    }

    public boolean isEnd() {
        return false;
    }

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws IOException, InvalidCommandException, InvalidDateException, MissingArgumentException, DukeIOException; //Referenced from Marcus Ong Wee's code

    public abstract Task getTask();

}
