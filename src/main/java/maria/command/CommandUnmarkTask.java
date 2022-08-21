package maria.command;

import maria.Storage;
import maria.Ui;
import maria.task.TaskList;

public class CommandUnmarkTask extends Command {

    private int index;

    public CommandUnmarkTask(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {

        taskList.get(this.index - 1).setDone(false);
        taskList.mutatedTask();
        ui.showText("Your task " + taskList.get(this.index - 1) + " has been un-completed.");

    }
}