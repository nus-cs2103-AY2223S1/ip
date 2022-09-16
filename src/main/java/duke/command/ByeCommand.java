package duke.command;

import java.util.concurrent.CompletableFuture;

import duke.TaskList;
import duke.constants.Constants;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * Terminates the program and show a bye message
 */
public class ByeCommand extends Command {

    @Override
    public String execute(TaskList tasks, Storage storage, Ui ui) {
        CompletableFuture.runAsync(this::terminate);
        return Constants.BYE_MESSAGE;
    }

    /**
     * Terminates the program while showing an exit message for 850 milliseconds
     */
    private void terminate() {
        try {
            Thread.sleep(850);
        } catch (InterruptedException e) {
            System.out.println("Unable to set delay.");
        }
        System.exit(0);
    }


}
