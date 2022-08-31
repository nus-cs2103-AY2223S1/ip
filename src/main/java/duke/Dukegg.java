package duke;

import commands.Command;
import exceptions.DukeException;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.util.Duration;

/**
 * Main class for chatbot.
 */
public class Dukegg {
    /**
     * Time it takes for application to shut down, in seconds, after executing this command
     */
    private static final int APPLICATION_TERMINATION_DELAY = 2;

    private final Ui ui;
    private final Storage storage;
    private final TaskList tasks;

    /**
     * Constructs a new chatbot which uses some file path as a way to store tasks.
     *
     * @param filePath The specified file path.
     */
    public Dukegg(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);

        TaskList currentTasks;
        try {
            currentTasks = new TaskList(storage.load());
        } catch (DukeException exception) {
            ui.showLoadingError();
            currentTasks = new TaskList();
        }
        this.tasks = currentTasks;
    }

    /**
     * Generates the response based on user input.
     *
     * @param input The specified user input.
     * @return The response after parsing the user's input..
     */
    public String getResponse(String input) {
        try {
            Command command = Parser.parse(input);
            String response = command.execute(this.tasks, this.ui, this.storage);
            if (command.isExit()) {
                terminateChatbot();
            }
            return response;
        } catch (DukeException exception) {
            return ui.showError(exception.toString());
        }
    }

    /**
     * Terminates the chatbot after a specified number of seconds.
     */
    private void terminateChatbot() {
        PauseTransition delay = new PauseTransition(Duration.seconds(APPLICATION_TERMINATION_DELAY));
        delay.setOnFinished(event -> Platform.exit());
        delay.play();
    }

    public String getGreetingMessage() {
        return this.ui.showWelcome();
    }
}
