package command;

import henry.Task;

/**
 * Responsible for setting the state of a task
 * to "incomplete".
 */
public class UnmarkCommand extends Command {

    public static final String COMMAND_WORD = "unmark";
    private static final String MESSAGE_SUCCESS = "I'VE UNMARKED THIS TASK AS DONE:\n %1$s.";
    private final int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public CommandResult execute() {
        Task task = taskList.getTasks().get(index);
        task.setComplete(false);
        taskList.getTasks().set(index, task);
        return new CommandResult(String.format(MESSAGE_SUCCESS, task));
    }
}
