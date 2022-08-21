package command;

import henry.Task;

import java.time.LocalDateTime;

public class DeadlineCommand extends Command {

    public static final String COMMAND_WORD = "deadline";
    public static final String MESSAGE_SUCCESS = "OK. I ADDED THIS TASK TO MY LIST:\n\t\t\t: %1$s.";
    private final Task task;

    public DeadlineCommand(String description, LocalDateTime dateTime) {
        this.task = new Task(Commands.TODO, description, dateTime);
    }

    @Override
    public CommandResult execute() {
        return new CommandResult(String.format(MESSAGE_SUCCESS, task));
    }
}
