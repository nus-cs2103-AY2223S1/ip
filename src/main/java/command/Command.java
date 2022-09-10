package command;

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

    public abstract void execute(TaskList tasks, Ui ui, Storage storage); //Referenced from Marcus Ong Wee's code

    public abstract Task getTask();

    
}
