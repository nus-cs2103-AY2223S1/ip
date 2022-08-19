package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;

import java.io.IOException;

public class TodoCommand extends Command {
    private final String description;

    public TodoCommand(String description) {
        this.description = description;
    }

    @Override
    public boolean isTerminal() {
        return false;
    }

    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) throws IOException {
        Task task = new Todo(description);
        tasks.addTask(task);
        ui.showMessage("I've added this task.")
                .showMessage(task.toString())
                .showTaskListSize(tasks);
        storage.save(tasks);
    }
}
