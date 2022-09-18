package duke.command;

import duke.storage.Storage;
import duke.tasklist.TaskList;

import java.util.Timer;
import java.util.TimerTask;

import static java.lang.System.exit;

/**
 * Represents a command to end the program.
 */
public class ExitCommand extends Command {
    /**
     * Class constructor. Construct an object representing exitCommand.
     * @param taskList The target taskList that will be deleted task.
     * @param storage The object containing the corresponding file.
     */
    @Override
    public String execute(TaskList taskList, Storage storage, CommandType c) {
        super.setIsExitTrue();
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                exit(0);
            }
        }, 1200);
        String goodbyeMessage = "Goodbye, traveller.";
        return goodbyeMessage;
    }
}
