package blob.commands;

import blob.common.Messages;
import blob.exception.InvalidDateFormatException;
import blob.tasks.Deadline;

/**
 * The DeadlineCommand class represents the command to create a deadline task.
 */
public class DeadlineCommand extends TaskCommand {

    /** The description of the deadline task to be created */
    private String taskDescription;

    /** The string representation of the date of the deadline task to be created */
    private String by;

    /**
     * Returns a command that when executed will attempt to add a deadline to the task list.
     *
     * @param taskDescription The description of the deadline task to be created.
     * @param by The string representation of the date of the deadline task to be created.
     */
    public DeadlineCommand(String taskDescription, String by) {
        super("deadline");
        this.taskDescription = taskDescription;
        this.by = by;
    }

    /**
     * {@inheritDoc}
     */
    public CommandResult execute() {
        try {
            Deadline task = new Deadline(taskDescription, by);
            this.taskList.addTask(task);
            return new CommandResult(Messages.MESSAGE_TASK_ADDED,
                    String.format("\n\t\t%s \n", task),
                    String.format(Messages.MESSAGE_TASK_LIST_SIZE, taskList.getNumberOfTasks()));
        } catch (InvalidDateFormatException exception) {
            return new CommandResult(exception.getBlobMessages());
        }
    }
}
