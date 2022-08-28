package duke;

import java.io.IOException;

/**
 * Adds a task to the task list.
 */
public class AddCommand extends Command {
    private Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.add(task);
        try {
            storage.save(tasks);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        ui.showAddTask(task, tasks.size());
    }
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof AddCommand) {
            AddCommand other = (AddCommand) obj;
            return this.task.equals(other.task);
        } else {
            return false;
        }
    }
}
