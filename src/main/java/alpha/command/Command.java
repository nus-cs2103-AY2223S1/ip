package alpha.command;

import alpha.AlphaException;
import alpha.FileOperations;
import alpha.TaskList;
import alpha.Ui;

import java.io.IOException;
public abstract class Command {
    public abstract void execute(TaskList tasks, Ui uI, FileOperations fileOperations) throws AlphaException;
}
