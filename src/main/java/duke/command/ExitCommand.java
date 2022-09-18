package duke.command;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

import duke.Storage;
import duke.Ui;
import duke.task.TaskList;


/**
 * Command that tells the App the terminate
 */
public class ExitCommand extends Command {

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        try { // saves after bye-command
            storage.save(tasks);
            CompletableFuture.runAsync(() -> {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.exit(0);
            });
            return "Changes saved, exiting in 5s";
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @return true
     */
    @Override
    public boolean isExit() {
        return true;
    }

    /**
     * @param o Other object we are comparing with
     * @return whether each objects are of the same type
     */
    @Override
    public boolean equals(Object o) {
        return o instanceof ExitCommand;
    }
}
