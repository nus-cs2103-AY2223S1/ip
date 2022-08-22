package duke.command;

import duke.DukeException;
import duke.task.Task;
import duke.task.TaskList;
import duke.util.UI;

public class UpdateStatusCommand extends Command {
    private final String WRONG_ARGUMENT = "This command expects a number argument!";
    private final String INDEX_OUT_OF_BOUND = "This command expects an index between 1 and number of tasks.";

    private final boolean isDone;

    public UpdateStatusCommand(String[] args, boolean isDone) {
        super(CommandType.UPDATE_STATUS, args);
        this.isDone = isDone;
    }

    @Override
    public void runSpecialTask(TaskList tasks) throws DukeException {
        int index;
        try {
            index = Integer.parseInt(args[0]) - 1;
        } catch (NumberFormatException e) {
            throw new DukeException(WRONG_ARGUMENT);
        }
        if (index < 0 || index >= tasks.size()) {
            throw new DukeException(INDEX_OUT_OF_BOUND);
        }
        Task task = tasks.get(index);
        task.setDone(isDone);
        tasks.save();
        UI.print(String.format("I've %s this task\n\t", this.isDone ? "checked" : "unchecked") + task);
    }
}
