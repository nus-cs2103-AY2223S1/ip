package duke.command;

import java.util.concurrent.CompletableFuture;

import duke.TaskList;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * Command to exit the program and show bye message
 */
public class ByeCommand extends Command {
    public ByeCommand() {
        super();
    }

    @Override
    public String execute(TaskList tasks, Storage storage, Ui ui) {
        CompletableFuture.runAsync(this::terminate);
        return "Bye! Deadline Duck hopes that you have a great day! Come back again!";
    }

    private void terminate() {
        try {
            Thread.sleep(850);
        } catch (InterruptedException e) {
            System.out.println("Unable to set delay.");
        }
        System.exit(0);
    }


}
