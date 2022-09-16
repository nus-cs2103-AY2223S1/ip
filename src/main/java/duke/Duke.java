package duke;

import duke.commands.Command;
import duke.exception.DukeException;
import duke.gui.Ui;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.util.Duration;

/**
 * Main class of chatbot Duke.
 */
public class Duke {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    /**
     * Creates a new instance of Duke.
     */
    public Duke() {
        this.storage = new Storage();
        this.ui = new Ui();
        try {
            this.taskList = new TaskList(storage.loadTasks());
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            this.taskList = new TaskList();
        }
    }

    /**
     * Gets the input from user and executes the corresponding command.
     *
     * @param input The text input from user.
     * @return The response after executing the command.
     */
    public String getResponse(String input) {
        assert input != null : "Command should not be null";
        try {
            Command command = Parser.parse(input);
            String response = command.execute(this.taskList, this.ui, this.storage);
            if (command.isExit()) {
                exitDuke();
            }
            assert !response.equals("") : "Response should not be empty";
            return response;
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

    /**
     * Terminates Duke application after 1 second.
     */
    private void exitDuke() {
        int delayDuration = 1;
        PauseTransition delay = new PauseTransition(Duration.seconds(delayDuration));
        delay.setOnFinished(event -> Platform.exit());
        delay.play();
    }

    public static void main(String[] args) {
        new Duke();
    }
}
