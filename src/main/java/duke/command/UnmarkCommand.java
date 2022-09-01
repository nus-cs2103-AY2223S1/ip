package duke.command;

import duke.task.Task;
import duke.util.MessagePrinter;
import duke.util.Storage;
import duke.util.TaskList;

/**
 * Represents a Command to mark a Task not done in Duke.
 */
public class UnmarkCommand extends Command {
    /**
     * The index of the Task to be marked as not done in TaskList.
     */
    private final int idTask;

    /**
     * The constructor of the Class.
     * @param idTask The index of the Task to be marked as not done in TaskList.
     */
    protected UnmarkCommand(int idTask) {
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
     * Execute the Command with given Duke Segments.
     * @param taskList TaskList of the Duke.
     * @param messagePrinter MessagePrinter of the Duke.
     * @param storage Storage of the Duke.
     */
    @Override
    public String execute(TaskList taskList, MessagePrinter messagePrinter, Storage storage) {
        String successMsg = "OK, I've marked this task as not done yet:";
        Task task = taskList.get(idTask - 1);
        task.markAsNotDone();
        return messagePrinter.printMessage(successMsg + "\n" + task);
    }

    /**
     * Returns the standard format of the Command.
     * @return String of standard format.
     */
    @Override
    public String getFormat() {
        return "unmark [ID of task]";
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
     * Return boolean indicating whether this object
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
        if (obj instanceof UnmarkCommand) {
            UnmarkCommand c = (UnmarkCommand) obj;
            return this.idTask == c.getIdTask();
        }
        return false;
    }
}
