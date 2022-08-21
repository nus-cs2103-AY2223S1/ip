package command;

import henry.Task;

public class MarkCommand extends Command {

    public static final String COMMAND_WORD = "mark";

    public static final String MESSAGE_SUCCESS = "I'VE MARKED THIS TASK AS DONE:\n\t\t\t: %1$s.";
    private final int index;
    private final Task task;

    public MarkCommand(int index) {
        this.index = index;
        this.task = taskList.getTasks().get(index);
    }

    @Override
    public CommandResult execute() {
        task.setComplete(true);
        taskList.getTasks().set(index, task);
        return new CommandResult(String.format(MESSAGE_SUCCESS, task));
    }
}
