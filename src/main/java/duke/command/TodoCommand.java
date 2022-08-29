package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.task.Todo;
import duke.Ui;

public class TodoCommand extends Command {

    private String description;

    public TodoCommand(String description) {
        this.description = description;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Todo todo = new Todo(description);
        tasks.add(todo);
        ui.todoTask(tasks, todo);
        storage.update(tasks.getTasks());
    }
}
