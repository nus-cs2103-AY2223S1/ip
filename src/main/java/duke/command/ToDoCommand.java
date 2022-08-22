package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class ToDoCommand extends Command {
    private String description;

    public ToDoCommand(String description) {
        super(false);
        this.description = description;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addToDo(description);
        int size = tasks.getSize();
        ui.showAdd(tasks.getTask(size - 1), size);
        storage.save(tasks.saveToStorage());
    }
}
