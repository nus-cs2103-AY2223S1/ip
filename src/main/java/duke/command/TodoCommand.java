package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class TodoCommand extends Command {
    String description;

    public TodoCommand(String description) {
        super();
        this.description = description;
    }

    @Override
    public void run(TaskList tasks, Ui ui, Storage storage) {
        String task = tasks.addToDo(this.description);
        ui.printAdd(task, tasks.getSize());
        storage.save(tasks.getTaskList());
    }
}
