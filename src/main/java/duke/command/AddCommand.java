package duke.command;

import duke.task.Task;
import duke.task.TaskList;

public class AddCommand extends Command{
    private final Task task;

    public AddCommand (Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList tasks) {
        tasks.addTask(this.task);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
