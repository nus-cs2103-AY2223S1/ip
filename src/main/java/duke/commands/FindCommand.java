package duke.commands;

import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.ui.Ui;

import java.util.List;

public class FindCommand extends Command {

    private String toFind;
    private TaskList taskList;
    private Ui ui;

    public FindCommand(String toFind, TaskList taskList, Ui ui) {
        this.toFind = toFind;
        this.taskList = taskList;
        this.ui = ui;
    }

    public String execute() {
        List<Task> found = taskList.find(toFind);
        return ui.printOnFind(found);
    }
}
