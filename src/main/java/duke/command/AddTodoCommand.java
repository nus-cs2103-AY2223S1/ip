package duke.command;

import duke.errors.DukeException;
import duke.main.Storage;
import duke.main.Ui;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.ToDos;

public class AddTodoCommand extends Command {
    private String description;

    public AddTodoCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task task = new ToDos(description);
        tasks.add(task);
        ui.addSuccess(task, tasks);
        storage.save(tasks);
    }
}
