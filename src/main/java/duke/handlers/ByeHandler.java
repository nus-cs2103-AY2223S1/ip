package duke.handlers;

import java.util.concurrent.CompletableFuture;

import duke.service.Service;
import javafx.application.Platform;

/** Handles the user action for exiting the application */
public class ByeHandler implements IHandler {
    /**
     * Handles the "mark" command which marks a task as done.
     *
     * @param s Service object of the application
     */
    @Override
    public String handle(Service s) {
        CompletableFuture.runAsync(() -> {
            try {
                Thread.sleep(2000);
                Platform.exit();
            } catch (InterruptedException ex) {
                Platform.exit();
            }
        });
        return "Bye. Hope to see you again soon!";
    }
}
