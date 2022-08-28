package Commands;

import Duke.Storage;
import Duke.TaskList;
import Duke.Ui;

import Tasks.ToDo;

/**
 * ToDo command which inherits from Command
 */
public class ToDoCommand extends Command {
    private String desc;

    public ToDoCommand(String desc) {
        super();
        this.desc = desc;
    }

    @Override
    public String execute(TaskList t, Ui ui, Storage storage) {
        ToDo todo = new ToDo(this.desc);
        t.addTask(todo);
        storage.save(t.stringfy());
        return ui.printAddTask(todo, t.getSize());
    }
}
