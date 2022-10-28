package command;

import java.time.LocalDateTime;

import henry.Task;

/**
 * Responsible for adding Event tasks to the task list.
 * Event tasks are tasks with a date and time.
 */
public class EventCommand extends Command implements TaskCommand {

    public static final String COMMAND_WORD = "event";
    private static final String MESSAGE_SUCCESS = "OK, I added this task to my list:\n %1$s";
    private final Task task;
    private final LocalDateTime dateTime;

    /**
     * Creates an EventCommand with the given description and dateTime.
     *
     * @param description   the description of the task
     * @param givenDateTime the date/time when the event will occur
     */
    public EventCommand(String description, LocalDateTime givenDateTime) {
        this.task = new Task(Commands.EVENT, description, givenDateTime);
        this.dateTime = givenDateTime;
    }

    @Override
    public Task getTask() {
        return task;
    }

    @Override
    public CommandResult execute() {
        task.addTentativeDate(dateTime);
        return new CommandResult(String.format(MESSAGE_SUCCESS, taskList.addTask(task)), taskList);
    }
}
