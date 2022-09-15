package command;

import duke.TaskList;
import duke.Ui;
import task.Task;

public class DeleteCommand extends Command {
    private TaskList taskList;
    private int index;
    private Ui ui;

    public DeleteCommand(TaskList taskList, int index) {
        this.taskList = taskList;
        this.index = index;
        this.ui = new Ui();
    }

    @Override
    public String execute() {
        Task task = taskList.get(index);
        taskList.remove(index);
        String taskName = task.toString();
        return ui.deleteMessage(taskName, taskList.size());
    }
}