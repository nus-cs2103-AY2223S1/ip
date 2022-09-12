package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.ui.Ui;

public class ByeCommand extends Command{

    private Ui ui;
    private Storage storage;
    private TaskList taskList;

    public ByeCommand(Ui ui, Storage storage, TaskList taskList) {
        this.ui = ui;
        this.storage = storage;
        this.taskList = taskList;
    }

    public String execute() {
        storage.write(taskList);
        return ui.bye();

    }
}
