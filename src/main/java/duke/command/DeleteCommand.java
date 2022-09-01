package duke.command;

import duke.task.Task;
import duke.util.MessagePrinter;
import duke.util.Storage;
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
     * The constructor of the class.
     * @param idTask The index of Task to be deleted in the TaskList.
     */
    protected DeleteCommand(int idTask) {
        super(Action.DELETE);
        this.idTask = idTask;
    }

    /**
     * Return the index of Task to be deleted in the TaskList.
     * @return The index of Task to be deleted in the TaskList.
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
        String successMsg = "Noted. I've removed this task:";
        Task task = taskList.remove(idTask - 1);
        successMsg = successMsg + "\n" + task + "\n"
                + "Now you have " + taskList.size() + " tasks in the list.";
        return messagePrinter.printMessage(successMsg);
    }

    /**
     * Returns the standard format of the Command.
     * @return String of standard format.
     */
    @Override
    public String getFormat() {
        return "delete [ID of Task]";
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
        if (obj instanceof DeleteCommand) {
            DeleteCommand c = (DeleteCommand) obj;
            return this.idTask == c.getIdTask();
        }
        return false;
    }
}
