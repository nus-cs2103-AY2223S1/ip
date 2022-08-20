package Commands;

import Duke.Storage;
import Duke.TaskList;
import Duke.Ui;

import Tasks.ToDo;

public class ToDoCommand extends Command {
    private String desc;

    public ToDoCommand(String desc) {
        super();
        this.desc = desc;
    }

    @Override
    public void execute(TaskList t, Ui ui, Storage storage) {
        ToDo todo = new ToDo(this.desc);
        t.addTask(todo);
        storage.save(t.stringfy());
        ui.printAddTask(todo, t.getSize());
    }
}
