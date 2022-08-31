package commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.util.Duration;

/**
 * Terminates the chatbot program after execution, and saves the current tasks to some local storage.
 */
public class ByeCommand extends Command {
    /**
     * Time it takes for application to shut down, in seconds, after executing this command
     */
    private static final int APPLICATION_TERMINATION_DELAY = 2;

    /**
     * Terminates the chatbot program and saves current task to the local storage.
     * <p>
     * {@inheritDoc}
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        storage.saveTasks(tasks);
        // End application
        PauseTransition delay = new PauseTransition(Duration.seconds(APPLICATION_TERMINATION_DELAY));
        delay.setOnFinished(event -> Platform.exit());
        delay.play();
        return ui.showGoodbye();
    }

    /**
     * {@inheritDoc}
     */
    public boolean isExit() {
        return true;
    }
}
