package duke.commands;

import duke.TaskList;
import duke.ui.Ui;

public class ListCommand extends Command{

    private Ui ui;
    private TaskList taskList;

    public ListCommand(Ui ui, TaskList taskList) {
        this.ui = ui;
        this.taskList = taskList;
    }

    public String execute() {
        return ui.printTaskList(taskList);
    }
}

