package command;

import java.time.LocalDateTime;

import henry.Task;

/**
 * Responsible for adding Event tasks to the task list.
 * Event tasks are tasks with a date and time.
 */
public class EventCommand extends Command {

    public static final String COMMAND_WORD = "event";
    private static final String MESSAGE_SUCCESS = "OK. I ADDED THIS TASK TO MY LIST:\n %1$s.";
    private final Task task;

    /**
     * Creates an EventCommand with the given description and dateTime.
     * @param description the description of the task
     * @param dateTime the date/time when the event will occur
     */
    public EventCommand(String description, LocalDateTime dateTime) {
        this.task = new Task(Commands.EVENT, description, dateTime);
    }

    @Override
    public CommandResult execute() {
        return new CommandResult(String.format(MESSAGE_SUCCESS, taskList.addTask(task)), taskList);
    }
}
