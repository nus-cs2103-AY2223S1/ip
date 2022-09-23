package duke.command;

import duke.Duke;
import duke.task.Task;
import duke.util.MessagePrinter;
import duke.util.TaskList;

/**
 * Represents a Command to delete existing Task in Duke.
 */
public class DeleteCommand extends Command {
    /**
     * The index of Task to be deleted in the TaskList.
     */
    private final int idTask;

    /**
     * Constructs the class.
     * @param idTask The index of Task to be deleted in the TaskList.
     */
    public DeleteCommand(int idTask) {
        super(Action.DELETE);
        this.idTask = idTask;
    }

    /**
     * Returns the index of Task to be deleted in the TaskList.
     * @return The index of Task to be deleted in the TaskList.
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
        String successMsg = "Noted. I've removed this task:";
        Task task = taskList.remove(idTask - 1);
        successMsg = successMsg + "\n" + task + "\n"
                + "Now you have " + taskList.size() + " tasks in the list.";
        return messagePrinter.getPrintMessage(successMsg);
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
