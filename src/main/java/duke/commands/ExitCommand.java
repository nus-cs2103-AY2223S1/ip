package duke.commands;

import java.util.Timer;
import java.util.TimerTask;

import duke.utils.Storage;
import duke.utils.TaskList;
import javafx.application.Platform;

/**
 * Represents an executable command to exit the program.
 *
 * @author sikai00
 */
public class ExitCommand extends Command {
    /** Command identifier used by Parser **/
    public static final String COMMAND_WORD = "bye";
    public static final String MESSAGE_USAGE = "Exit KarenBot:\n"
            + "    exit\n";

    /**
     * {@inheritDoc}
     */
    @Override
    public CommandResult execute(TaskList taskList, Storage storage) {
        String msg = "Go away, don't come back!\n(exiting in a second...)";
        storage.writeAllToStorage(taskList);
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.exit();
                System.exit(0); // Platform.exit() does not end the run
            }
        }, 1000);
        return new CommandResult(msg);
    }
}
