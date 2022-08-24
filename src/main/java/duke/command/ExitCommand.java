package duke.command;

import duke.task.TasksController;
import duke.Ui;
import duke.Storage;

/**
 * ExitCommand class will execute the exit command
 */
public class ExitCommand extends Command {

    /**
     * Exits the app
     * @param controller Duke task controller
     * @param ui Duke Ui
     * @param storage Duke IO processor
     */
    public void execute(TasksController controller, Ui ui, Storage storage) {
        ui.sayBye();
        storage.save(controller.getTasks());
        System.exit(0);
    }
}
