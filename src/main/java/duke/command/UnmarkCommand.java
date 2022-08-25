package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.task.Task;

public class UnmarkCommand extends Command {
    private int num;
    public UnmarkCommand(String info, int num) {
        super(info);
        this.num = num;
    }

    public int getNum() {
        return num;
    }

    @Override
    public void execute(Ui ui, TaskList taskList) {
        Task task = taskList.getTask(num);
        task.setUnmarked();
        ui.showUnmarkMessage(task);
    }
}
