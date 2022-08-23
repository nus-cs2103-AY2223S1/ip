package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.Todo;


public class TodoCommand extends Command{
    private String event;

    public TodoCommand(String event) {
        super();
        this.event = event;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Todo e = new Todo(this.event);
        tasks.addTask(e);
        storage.savetoFile(tasks.saveList());
        ui.printAddTask(e, tasks.getSize());
    }
}
