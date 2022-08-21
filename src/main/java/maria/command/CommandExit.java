package maria.command;

import maria.Storage;
import maria.Ui;
import maria.task.TaskList;

public class CommandExit extends Command {

    /**
     * Executes the command.
     * @param taskList The list of all the tasks
     * @param ui The user interface object
     * @param storage The storage object
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showText("Adiós, hasta luego!");
        System.exit(0);
    }
}
