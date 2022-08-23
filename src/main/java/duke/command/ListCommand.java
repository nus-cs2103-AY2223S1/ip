package duke.command;

import duke.MessagePrinter;
import duke.Storage;
import duke.TaskList;

public class ListCommand extends Command {
    protected ListCommand() {
        super(Action.LIST);
    }

    @Override
    public void execute(TaskList taskList, MessagePrinter messagePrinter, Storage storage) {
        String message = "Here are the tasks in your list:\n";
        if (taskList.size() == 0) {
            message = "Currently no tasks in the list.";
        } else {
            message = message + taskList.toString();
        }
        messagePrinter.printMessage(message);
    }

    @Override
    public String getFormat() {
        return "list";
    }

    @Override
    public boolean isTerminated() {
        return false;
    }
}
