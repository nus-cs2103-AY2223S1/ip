package duke.command;

import duke.Duke;
import duke.task.Task;
import duke.util.MessagePrinter;
import duke.util.TaskList;

/**
 * Represents a Command to mark a Task not done in Duke.
 */
public class UnmarkCommand extends Command {
    private final int idTask;

    /**
     * Constructs the class.
     * @param idTask The index of the Task to be marked as not done in TaskList.
     */
    public UnmarkCommand(int idTask) {
        super(Action.UNMARK);
        this.idTask = idTask;
    }

    /**
     * Returns the index of the Task to be marked as not done in TaskList.
     * @return The index of the Task to be marked as not done in TaskList.
     */
    public int getIdTask() {
        return idTask;
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
        String successMsg = "OK, I've marked this task as not done yet:";
        Task task = taskList.get(idTask - 1);
        task.markAsNotDone();
        return messagePrinter.getPrintMessage(successMsg + "\n" + task);
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
