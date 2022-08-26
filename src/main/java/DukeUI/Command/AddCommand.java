package DukeUI.Command;

import DukeUI.TaskList;
import DukeUI.Ui;
import DukeUI.FileStorage.Storage;
import DukeUI.Task.Task;

public class AddCommand extends Command{

    private Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.insertTask(this.task);
        storage.writeListToFile(tasks);
        ui.showResponse(String.format("%s\nNow you have %d tasks in the list.", 
                this.toString(), tasks.getNumOfTasks()));
    }

    @Override public String toString() {
        return String.format("Got it. I've added this task:\n    %s", this.task.toString());
    }
}
