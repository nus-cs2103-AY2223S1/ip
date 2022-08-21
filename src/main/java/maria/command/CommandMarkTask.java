package maria.command;

import maria.Storage;
import maria.Ui;
import maria.task.TaskList;

public class CommandMarkTask extends Command {

    private int index;

    public CommandMarkTask(int index) {
        this.index = index;
    }

    /**
     * Executes the command.
     * @param taskList The list of all the tasks
     * @param ui The user interface object
     * @param storage The storage object
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {

        taskList.get(this.index - 1).setDone(true);
        taskList.mutatedTask();
        ui.showText("Your task " + taskList.get(this.index - 1) + " has been completed.");

    }
}