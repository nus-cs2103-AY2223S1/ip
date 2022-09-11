package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.util.Duration;


/**
 * Entry point of the Duke Chat Bot application
 * Initializes the application and starts the interaction with the user.
 */
public class Duke {
    /** Ui class that prints terminal output. */
    private Ui ui;

    /** The list of tasks. */
    private TaskList tasks;

    /** Constructor for Class Duke/ */
    public Duke() {
        this.ui = new Ui();
        try {
            this.tasks = Storage.load();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * Gets the response from bot.
     * @param input The input given by the user.
     * @return  The response given by bot.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parseCommand(input);
            if (c.isExit()) {
                PauseTransition delay = new PauseTransition(Duration.seconds(3));
                delay.setOnFinished(event -> Platform.exit());
                delay.play();
            }
            String response = c.execute(this.tasks);
            return response;
        } catch (DukeException e) {
            return ui.showError(e);
        }
    }
}
