package duke.command;

import java.util.Timer;
import java.util.TimerTask;

import duke.storage.Storage;
import duke.tasklist.TaskList;


/**
 * Encapsulates saving and quitting Artemis.
 *
 * @author Kartikeya
 */
public class ExitCommand implements Command {
    /**
     * {@inheritDoc}
     * Saves itemList to storage, shows outro to the user and exits from Artemis.
     */
    @Override
    public String execute(TaskList itemList, Storage storage) {
        itemList.save(storage);
        //@@author kxrt-reused
        // Reused from RezwanArefin01 following discussion
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                System.exit(0);
            }
        }, 1200);
        //@@author
        return "Goodbye, see you soon!";
    }
}
