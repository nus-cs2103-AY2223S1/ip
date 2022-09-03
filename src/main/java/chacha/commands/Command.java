package chacha.commands;

import java.util.ArrayList;

import chacha.Storage;
import chacha.TaskList;
import chacha.Ui;
import chacha.tasks.Task;

public abstract class Command {

    public abstract void execute(TaskList taskList, Ui ui);

    public abstract boolean isExit();
    
}
