package duke.command;

import duke.DukeException;
import duke.task.Task;

public class UnmarkCommand extends Command {
    private int taskIndex;

    public UnmarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute() throws DukeException {
        Task unmarkedTask = Command.taskList.unmarkTask(this.taskIndex);
        Command.ui.printMessages(new String[]{"Ok, I've marked this task as not done yet:", unmarkedTask.toString()});
    }
}
