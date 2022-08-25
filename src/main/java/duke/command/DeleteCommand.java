package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.task.Task;

public class DeleteCommand extends Command {

    private int num;

    public DeleteCommand(String info, int num) {
        super(info);
        this.num = num;
    }

    public int getNum() {
        return num;
    }

    @Override
    public void execute(Ui ui, TaskList taskList) {
        Task task = taskList.getTask(this.num);
        taskList.remove(this.num);
        int size = taskList.getSize();
        ui.showDeleteMessage(task, size);
    }
}
