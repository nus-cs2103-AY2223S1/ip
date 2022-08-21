package command;

import henry.Task;

public class UnmarkCommand extends Command {

    public static final String COMMAND_WORD = "unmark";
    public static final String MESSAGE_SUCCESS = "I'VE UNMARKED THIS TASK AS DONE:\n\t\t\t: %1$s.";
    private final int index;
    private final Task task;

    public UnmarkCommand(int index) {
        this.index = index;
        this.task = taskList.getTasks().get(index);
    }

    @Override
    public CommandResult execute() {
        task.setComplete(false);
        taskList.getTasks().set(index, task);
        return new CommandResult(String.format(MESSAGE_SUCCESS, task));
    }
}
