package duke.commands;

import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.ui.Ui;

public class UnmarkCommand extends Command {

    private int idx;
    private TaskList taskList;
    private Ui ui;

    public UnmarkCommand(int idx, TaskList taskList, Ui ui) {
        this.idx = idx;
        this.taskList = taskList;
        this.ui = ui;
    }

    public String execute() {
        Task toUnmark = taskList.unmark(idx);
        return ui.printOnUnmark(toUnmark);
    }
}
