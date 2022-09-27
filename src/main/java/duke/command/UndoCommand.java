package duke.command;

import java.util.Stack;

import duke.Duke;
import duke.exception.DukeRuntimeException;
import duke.util.MessagePrinter;
import duke.util.TaskList;

/**
 * Represents a Command to revert the command executed in Duke.
 */
public class UndoCommand extends Command {
    /**
     * Constructs the class.
     */
    public UndoCommand() {
        super(Action.UNDO);
    }

    /**
     * Executes the Command with given Duke.
     * @param duke The target duke that the command takes effect.
     * @return The response of Duke.
     */
    @Override
    public String execute(Duke duke) {
        Stack<TaskList> taskListHistory = duke.getTaskListHistory();
        MessagePrinter messagePrinter = duke.getMessagePrinter();
        if (taskListHistory.size() < 1) {
            throw new DukeRuntimeException("No more change to the task list can be undone.");
        }
        TaskList lastStage = taskListHistory.pop();
        duke.setTaskList(lastStage);
        return messagePrinter.getPrintMessage("Successfully undo last modification to the TaskList.\n"
                + "Type [list] to view current Tasks");
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
