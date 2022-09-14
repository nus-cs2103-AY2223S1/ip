package command;

import henry.Task;

/**
 * Responsible for setting the state of a task
 * to "incomplete".
 */
public class UnmarkCommand extends Command {

    public static final String COMMAND_WORD = "unmark";
    private static final String MESSAGE_SUCCESS = "I've marked this task as not done:\n %1$s";
    private final int indexToUnmark;

    public UnmarkCommand(int index) {
        this.indexToUnmark = index;
    }

    @Override
    public CommandResult execute() {
        Task task = taskList.get(indexToUnmark);
        task.setComplete(false);
        taskList.set(indexToUnmark, task);
        return new CommandResult(String.format(MESSAGE_SUCCESS, task));
    }
}
