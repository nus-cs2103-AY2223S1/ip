package command;

import henry.Task;

/**
 * Responsible for setting the state of a task
 * to "complete".
 */
public class MarkCommand extends Command {

    public static final String COMMAND_WORD = "mark";

    private static final String MESSAGE_SUCCESS = "I'VE MARKED THIS TASK AS DONE:\n %1$s";
    private final int indexToMark;

    public MarkCommand(int index) {
        this.indexToMark = index;
    }

    @Override
    public CommandResult execute() {
        Task task = taskList.getTasks().get(indexToMark);
        task.setComplete(true);
        taskList.getTasks().set(indexToMark, task);
        return new CommandResult(String.format(MESSAGE_SUCCESS, task));
    }
}
