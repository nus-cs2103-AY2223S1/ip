package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

public class UnMarkedCommand extends Command {
    private int number;

    public UnMarkedCommand(int number) {
        this.number = number;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = tasks.getTaskList().get(this.number - 1);
        task.setUncompleted();
        ui.showUnMarked(task);
        storage.store(tasks.getTaskList());
    }
}
