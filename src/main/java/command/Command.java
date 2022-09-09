package command;

import main.Storage;
import main.TaskList;
import main.Ui;

public abstract class Command {

    protected String commandArgs;

    Command(String commandArgs) {
        this.commandArgs = commandArgs;
    }

    abstract void execute(TaskList tasks, Ui ui, Storage storage); //Referenced from Marcus Ong Wee's code
    
}
