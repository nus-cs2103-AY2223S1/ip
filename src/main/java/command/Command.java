package command;

import tasklist.TaskList;
import util.Storage;

public abstract class Command {
    private boolean isTerminated = false;

    public abstract void execute(TaskList list, Storage storage);

    public boolean getIsTerminated() {
        return isTerminated;
    }

    public void setIsTerminated(boolean isTerminated) {
        this.isTerminated = isTerminated;
    }
}
