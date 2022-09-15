package duke.commands;

import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.ui.Ui;

public class MarkCommand extends Command {

    private int idx;
    private TaskList taskList;
    private Ui ui;

    public MarkCommand(int idx, TaskList taskList, Ui ui) {
        this.idx = idx;
        this.taskList = taskList;
        this.ui = ui;
    }

    public String execute() {
        Task toMark = taskList.mark(idx);
        return ui.printOnMark(toMark);
    }
}
