package Command;

import Duke.Storage;
import Duke.TaskList;
import Duke.Ui;
import Tasks.Todo;

public class TodoCommand extends Command{
    private String desc;

    public TodoCommand(String desc) {
        super();
        this.desc = desc;
    }

    @Override
    public void execute(TaskList t, Ui ui, Storage storage) {
        Todo todo = new Todo(this.desc);
        t.addTask(todo);
        storage.writeFile(t.tasksToString());
        ui.printAddTask(todo, t.getSize());
    }
}
