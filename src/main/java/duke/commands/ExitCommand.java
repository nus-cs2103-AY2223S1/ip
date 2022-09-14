package duke.commands;

import java.util.concurrent.CompletableFuture;

import duke.data.TaskList;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * This class represents a command to exit from the application
 */
public class ExitCommand extends Command {

    /**
     * Checks if the command is an Exit Command
     * @return True if it is an Exit Command
     */
    @Override
    public boolean isExit() {
        return true;
    }

    /**
     * Executes the Command
     * @param ui Prints the messages based on the type of Command
     * @param storage Saves the modified list of tasks
     * @param taskList List of tasks
     */
    // reference: https://www.baeldung.com/java-delay-code-execution
    @Override
    public String execute(Ui ui, Storage storage, TaskList taskList) {
        CompletableFuture.runAsync(() -> {
            try {
                Thread.sleep(1000);
                System.exit(0);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        return ui.printExit();
    }
}
