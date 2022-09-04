package chacha.commands;


import chacha.Storage;
import chacha.TaskList;
import chacha.Ui;

public abstract class Command {

    public abstract void execute(TaskList taskList, Ui ui, Storage storage);

    public abstract boolean isExit();
    
}
