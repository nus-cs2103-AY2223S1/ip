package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;
import duke.task.Task;

public class DeleteCommand extends Command {
    private int num;
    public DeleteCommand(String cmd, int num) {
        super(cmd);
        this.num = num;
    }
    @Override
    public void execute(Ui ui, TaskList taskList) throws DukeException {
        Task task = taskList.getTask(this.num);
        taskList.remove(this.num);
        int size = taskList.getSize();
        ui.showDeleteMsg(task, size);
    }
}
