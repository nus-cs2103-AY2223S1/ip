package maria.command;

import maria.Storage;
import maria.Ui;
import maria.task.TaskList;

public class CommandRemoveTask extends Command {

    private int index;

    public CommandRemoveTask(int index) {
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

        String taskStr = taskList.get(this.index - 1).toString();
        taskList.remove(this.index - 1);
        ui.showText("Your task " + taskStr + " has been deleted.");

    }
}