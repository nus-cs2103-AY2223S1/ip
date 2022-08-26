package commands;

import duke.Task;
import duke.TaskList;
import duke.Ui;

public class DeleteCommand extends Command {
    private int target;
    private TaskList tasks;
    private Ui ui;

    public DeleteCommand(TaskList tasks, int target, Ui ui) {
        this.tasks = tasks;
        this.target = target;
        this.ui = ui;
    }

    public void execute() {
        Task toRemove = tasks.get(target);
        tasks.remove(target);
        ui.showRemoved(toRemove);
    }
}
