package commands;

import common.Messages;
import exception.InvalidDateFormatException;
import tasks.Event;

public class EventCommand extends TaskCommand {
    String taskDescription;
    String at;
    public EventCommand(String taskDescription, String at) {
        super("event");
        this.taskDescription = taskDescription;
        this.at = at;
    }

    public CommandResult execute() {
        try {
            Event task = new Event(taskDescription, at);
            this.taskList.addTask(task);
            return new CommandResult(Messages.MESSAGE_TASK_ADDED,
                    String.format("\n\t\t%s \n", task),
                    String.format(Messages.MESSAGE_TASK_LIST_SIZE, taskList.getNumberOfTasks()));
        } catch (InvalidDateFormatException exception) {
            return new CommandResult(exception.getBlobMessages());
        }
    }
}
