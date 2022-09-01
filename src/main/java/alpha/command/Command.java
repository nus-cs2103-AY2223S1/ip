package alpha.command;

import alpha.AlphaException;
import alpha.FileOperations;
import alpha.TaskList;
import alpha.Ui;

public abstract class Command {

    public abstract void execute(TaskList tasks, Ui uI, FileOperations fileOperations) throws AlphaException;
}
