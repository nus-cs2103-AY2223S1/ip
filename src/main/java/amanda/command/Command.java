package amanda.command;
import amanda.manager.*;
import amanda.exception.*;
import amanda.task.*;
import amanda.ui.*;

public class Command {

    protected Task task;
    protected int taskNo;
    public Command(Task task, int taskNo) {
        this.task = task;
        this.taskNo = taskNo;
    }

    public void execute(TaskManager tasks, Ui ui, StoreManager store) throws AmandaException {

    }

    public boolean isExit() {
        return this instanceof ExitCommand;
    }
}
