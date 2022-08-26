package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.ToDo;

public class ToDoCommand extends Command {
    private final ToDo toDo;

    public ToDoCommand(String description) {
        super();
        this.toDo = new ToDo(description);
    }

    @Override
    public void execCommand(TaskList list, Ui ui, Storage storage) {
        list.addTask(this.toDo);
        ui.showAdd(this.toDo, list.getSize());
        storage.saveList(list.save());
    }
}
