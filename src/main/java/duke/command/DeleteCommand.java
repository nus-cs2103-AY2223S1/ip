package duke.command;

import duke.TaskList;
import duke.Ui;

public class DeleteCommand extends Command {
    int idx;

    public DeleteCommand(int idx) {
        super();
        this.idx = idx;
    }

    @Override
    public void execute(TaskList tasks, Ui ui) {
        String task = tasks.deleteTask(this.idx);
        ui.showDelete(task, tasks.getSize());
    }
}