package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;
import duke.task.Task;

public class UnmarkCommand extends Command {
    private int num;
    public UnmarkCommand(String cmd, int num) {
        super(cmd);
        this.num = num;
    }
    public int getNum() {
        return num;
    }
    @Override
    public void execute(Ui ui, TaskList taskList) throws DukeException {
        Task task = taskList.getTask(num);
        task.unmarkT();
        ui.showUnmarkMsg(task);
    }
}
