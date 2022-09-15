package command;

import duke.TaskList;
import duke.Ui;
import task.Task;

public class UnmarkCommand extends Command {
    private TaskList taskList;
    private int index;
    private Ui ui;

    public UnmarkCommand(TaskList taskList, int index) {
        this.taskList = taskList;
        this.index = index;
        this.ui = new Ui();
    }

    @Override
    public String execute() {
        Task task = taskList.get(index);
        task.unmark();
        String taskName = task.toString();
        taskList.set(index, task);
        return ui.unmarkMessage(taskName);
    }
}