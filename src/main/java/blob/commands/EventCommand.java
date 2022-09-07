package blob.commands;

import blob.common.Messages;
import blob.exception.InvalidDateFormatException;
import blob.exception.InvalidPriorityException;
import blob.tasks.Event;

/**
 * The EventCommand class represents the command to create an event task.
 */
public class EventCommand extends TaskCommand {
    /** The priority of the event task to be created */
    private String taskPriority;

    /** The description of the event task to be created */
    private String taskDescription;

    /** The string representation of the date of the event task to be created */
    private String at;

    /**
     * Returns a command that when executed will attempt to add an event to the task list.
     *
     * @param taskDescription The description of the event task to be created.
     * @param at The string representation of the date of the event task to be created.
     */
    public EventCommand(String taskPriority, String taskDescription, String at) {
        super("event");
        this.taskPriority = taskPriority;
        this.taskDescription = taskDescription;
        this.at = at;
    }

    /**
     * {@inheritDoc}
     */
    public CommandResult execute() {
        try {
            Event task = new Event(taskDescription, at, taskPriority);
            this.taskList.addTask(task);
            return new CommandResult(Messages.MESSAGE_TASK_ADDED,
                    String.format("\n\t\t%s \n", task),
                    String.format(Messages.MESSAGE_TASK_LIST_SIZE, taskList.getNumberOfTasks()));
        } catch (InvalidDateFormatException exception) {
            return new CommandResult(exception.getBlobMessages());
        } catch (InvalidPriorityException exception) {
            return new CommandResult(exception.getBlobMessages());
        }
    }
}
