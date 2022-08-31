package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class TodoCommand extends Command {

    private String name;

    public TodoCommand(String name) {
        this.name = name;
    }

    public String execute(TaskList tasks, Storage storage) {
        tasks.add(tasks.createTask('T', name, null));
        return Ui.getAddTaskString(name, tasks.size());
    }

}
