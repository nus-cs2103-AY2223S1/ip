package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class TodoCommand extends Command {
    private String description;

    public TodoCommand(String description) {
        super();
        this.description = description;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String task = tasks.addTodo(description);
        ui.showAdd(task, tasks.getSize());
        storage.save(tasks.saveTasks());
    }
}
