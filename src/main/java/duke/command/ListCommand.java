package duke.command;

import duke.Duke;
import duke.util.MessagePrinter;
import duke.util.TaskList;

/**
 * Represents a Command to list all Tasks in Duke.
 */
public class ListCommand extends Command {
    /**
     * Constructs the class.
     */
    public ListCommand() {
        super(Action.LIST);
    }

    /**
     * Executes the Command with given Duke.
     * @param duke The target duke that the command takes effect.
     * @return The response of Duke.
     */
    @Override
    public String execute(Duke duke) {
        TaskList taskList = duke.getTaskList();
        MessagePrinter messagePrinter = duke.getMessagePrinter();
        String message = "Here are the tasks in your list:\n";
        if (taskList.size() == 0) {
            message = "Currently no tasks in the list.";
        } else {
            message = message + taskList;
        }
        return messagePrinter.getPrintMessage(message);
    }

    /**
     * Returns whether this command terminates Duke.
     * @return Returns whether this command terminates Duke.
     */
    @Override
    public boolean isTerminating() {
        return false;
    }
}
