package duke.command;
import duke.MessagePrinter;
import duke.Storage;
import duke.TaskList;
import duke.task.Task;

public abstract class AddCommand extends Command {
    String msg;

    public AddCommand(Action action, String message) {
        super(action);
        this.msg = message;
    }

    @Override
    public boolean isTerminated() {
        return false;
    }
}
