package duke.command;

import duke.task.Task;
import duke.task.TaskList;
import duke.util.Storage;
import duke.util.UI;

public class AddTaskCommand extends Command {

    private final Task task;

    public AddTaskCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(Storage storage, UI ui, TaskList tasks) {
        tasks.add(task);
        storage.save(tasks);
        ui.print("I've added the following task:\n\t" + task);
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof AddTaskCommand) {
            AddTaskCommand other = (AddTaskCommand) o;
            return this.task.equals(other.task);
        }
        return false;
    }
}
