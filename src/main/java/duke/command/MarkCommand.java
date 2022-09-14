package duke.command;

import duke.Duke;
import duke.task.Task;
import duke.util.MessagePrinter;
import duke.util.TaskList;

/**
 * Represents a Command to mark a Task as done in Duke.
 */
public class MarkCommand extends Command {
    private final int idTask;

    /**
     * The constructor of the Class.
     * @param idTask The index of the Task to be marked as done in TaskList.
     */
    public MarkCommand(int idTask) {
        super(Action.MARK);
        this.idTask = idTask;
    }

    /**
     * Returns the index of the Task to be marked as done in TaskList.
     * @return The index of the Task to be marked as done in TaskList.
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
        String successMsg = "Nice! I've marked this task as done:";
        Task task = taskList.get(idTask - 1);
        task.markAsDone();
        return messagePrinter.printMessage(successMsg + "\n" + task);
    }

    /**
     * Returns the standard format of the Command.
     * @return String of standard format.
     */
    @Override
    public String getFormat() {
        return "mark [ID of task]";
    }

    /**
     * Returns whether this command terminates Duke.
     * @return Returns whether this command terminates Duke.
     */
    @Override
    public boolean isTerminated() {
        return false;
    }

    /**
     * Returns boolean indicating whether this object
     * is equivalent to another object.
     *
     * @param obj The object to be checked.
     * @return The boolean whether the given object is equivalent to this object.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof MarkCommand) {
            MarkCommand c = (MarkCommand) obj;
            return this.idTask == c.getIdTask();
        }
        return false;
    }
}
