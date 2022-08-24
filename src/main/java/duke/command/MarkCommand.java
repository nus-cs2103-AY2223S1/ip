package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;
import duke.task.Task;

public class MarkCommand extends Command {
    private int num;
    public MarkCommand(String cmd, int num) {
        super(cmd);
        this.num = num;
    }
    @Override
    public void execute(Ui ui, TaskList taskList) throws DukeException {
        Task task = taskList.getTask(num);
        task.markT();
        ui.showMarkMsg(task);
    }
}
