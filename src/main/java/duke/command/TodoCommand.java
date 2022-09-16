package duke.command;

import duke.task.Task;
import duke.task.Todo;

public class TodoCommand extends Command {
    public TodoCommand(String parameters) {
        super((tasks, ui, storage) -> {
            Task task = new Todo(parameters);
            tasks.add(task);

            ui.showAddedTaskMessage(task);
            ui.countTasks(tasks);
        });
    }
}
