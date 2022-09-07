package duke.command;

import java.util.Timer;
import java.util.TimerTask;

import duke.Ui;
import duke.task.TaskList;

/**
 * Class to represent the bye command.
 */
public class ByeCommand extends Command {
    public static final String COMMAND = "bye";

    /**
     * Returns goodbye message and exit the program.
     *
     * @param taskList the current list of tasks.
     * @return the response message.
     */
    @Override
    public String executeAndGetResponse(TaskList taskList) {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                System.exit(0);
            }
        }, 1500);
        return Ui.getGoodbyeMessage();
    }
}
