package tako.command;

import tako.Storage;
import tako.TakoException;
import tako.TaskList;
import tako.Ui;

import java.io.IOException;

public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws IOException, TakoException;
    public abstract boolean isExit();
}
