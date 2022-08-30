package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class TodoCommand extends Command {

    private String name;

    public TodoCommand(String name) {
        this.name = name;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.add(tasks.createTask('T', name, null));
        ui.printAddTask(name, tasks.size());
    }

}
