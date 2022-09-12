package duke.command;

import java.util.Timer;
import java.util.TimerTask;

import duke.common.Messages;
import duke.exception.DukeException;
import duke.storage.Storage;
import duke.util.TaskList;

/**
 * Represents a command to exit the program; subclass of Command.
 * @author Huang Yuchen
 * @author hyuchen@u.nus.edu
 */
public class ExitCommand extends Command {
    /**
     * Executes the command for "bye" keyword.
     * This is the main way for outputting bot replies.
     *
     * @param storage        the storage object
     * @param tasklist       the task list object
     * @return               the bot reply
     * @throws DukeException if the user input is unrecognised
     */
    @Override
    public String execute(Storage storage, TaskList tasklist) throws DukeException {
        storage.writeToFile(tasklist);
        /*
          Code for exiting the program taken from
          https://stackoverflow.com/questions/15747277/how-to-make-java-program-exit-after-a-couple-of-seconds
         */
        Timer timer = new Timer();
        TimerTask exit = new TimerTask() {
            @Override
            public void run() {
                System.exit(0);
            }
        };
        timer.schedule(exit, 1000);
        return Messages.GOODBYE;
    }
}
