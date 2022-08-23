package duke.command;

import duke.utils.Storage;
import duke.TaskList;
import duke.utils.Ui;

public class ExitCommand extends Command{

    /**
     * duke.command.Command to exit the application
     */
    @Override
    public void run(TaskList taskList, Storage storage) {
        Ui.bye();
        System.exit(0);
    }
}
