package duke.command;

import duke.utils.Storage;
import duke.TaskList;
import duke.utils.Ui;

public class ExitCommand extends Command{

    /**
     * Exits the application
     */
    @Override
    public void run(TaskList taskList, Storage storage) {
        Ui.bye();
        System.exit(0);
    }
}
