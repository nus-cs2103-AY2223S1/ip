package command;

import java.time.LocalDateTime;

import henry.Task;

/**
 * Responsible for adding Deadline tasks to the task list.
 * Deadline tasks are tasks with a deadline date and time.
 */
public class DeadlineCommand extends Command {

    public static final String COMMAND_WORD = "deadline";
    private static final String MESSAGE_SUCCESS = "OK. I ADDED THIS TASK TO MY LIST:\n %1$s.";
    private final Task task;

    /**
     * Creates a new DeadlineCommand with the given description and dateTime.
     * @param description the description of the task
     * @param dateTime the date/time when the task is due
     */
    public DeadlineCommand(String description, LocalDateTime dateTime) {
        this.task = new Task(Commands.DEADLINE, description, dateTime);
    }

    @Override
    public CommandResult execute() {
        return new CommandResult(String.format(MESSAGE_SUCCESS, taskList.addTask(task)), taskList);
    }
}
