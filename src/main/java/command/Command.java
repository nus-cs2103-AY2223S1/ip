package command;

import tasklist.TaskList;
import util.Storage;

public abstract class Command {
    public boolean isTerminated = false;
    public abstract void execute(TaskList list, Storage storage);
}
