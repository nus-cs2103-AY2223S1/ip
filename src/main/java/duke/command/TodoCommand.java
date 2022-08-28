package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.Storage;

public class TodoCommand extends Command {

    String name;

    public TodoCommand(String name) {
        this.name = name;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.add(tasks.createTask('T', name, null));
        ui.printAddTask(name, tasks.size());
    }

}
