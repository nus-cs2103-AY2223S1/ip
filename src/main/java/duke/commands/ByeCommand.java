package duke.commands;

import duke.storage.Storage;
import duke.tasklist.TaskList;

import java.util.Timer;
import java.util.TimerTask;

public class ByeCommand extends Command {

    /**
     * Returns a String response from Duke after parsing and executing a bye command.
     * @param taskList List of tasks passed in to duke
     * @param storage Storage object to handle loading / saving from and to files
     * @return string response from Duke
     */
    @Override
    public String execute(TaskList taskList, Storage storage) {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                System.exit(0);
            }
        }, 2000);
        return "Bye! Hope to see you again soon!";
    }
}
