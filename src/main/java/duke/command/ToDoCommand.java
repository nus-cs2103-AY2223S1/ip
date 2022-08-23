package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.ToDo;

public class ToDoCommand extends Command {
    private final ToDo todo;

    public ToDoCommand(String description) {
        super();
        this.todo = new ToDo(description);
    }

    @Override
    public void execCommand(TaskList list, Ui ui, Storage storage) {
        list.addTask(this.todo);
        ui.showAdd(this.todo, list.getSize());
        storage.saveList(list.save());
    }
}
