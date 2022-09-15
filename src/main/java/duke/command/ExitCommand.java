package duke.command;

import duke.model.TaskList;
import duke.storage.Storage;
import duke.ui.Ui;

import java.util.Timer;
import java.util.TimerTask;

/**
 * A command the exit the program.
 */
public class ExitCommand extends Command {

    /**
     * Executes a Command.
     *
     * @param taskList A list of tasks.
     * @param storage A location to store the task information.
     */
    @Override
    public String execute(TaskList taskList, Storage storage) {
        toggleIsExit();
        String response = Ui.sayBye();
        TimerTask exitTimer = new TimerTask() {
            @Override
            public void run() {
                System.exit(0);
            }
        };
        new Timer().schedule(exitTimer, 1500);
        assert response != null : "Response should not be null";
        return response;
    }
}
