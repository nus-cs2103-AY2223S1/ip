package duke;

import duke.commands.Command;
import duke.exceptions.DukeException;
import duke.managers.ParserManager;
import duke.managers.StorageManager;
import duke.managers.TaskManager;
import duke.ui.Main;
import javafx.application.Application;

/**
 * Initializes the Duke application and initiates a conversation with the user.
 *
 * @author Emily Ong Hui Qi
 */
public class Duke {

    /**
     * Name of the chatbot
     */
    public static final String NAME = "Duke";

    /**
     * The greeting message used by the chatbot when the application starts
     */
    public static final String MESSAGE_GREETING = String.format("Hello! I'm %s\nWhat can I do for you?", Duke.NAME);

    private StorageManager storageManager;
    private TaskManager taskManager;
    private ParserManager parserManager;

    /**
     * Initializes the Duke application with managers.
     */
    public Duke() {
        try {
            this.storageManager = new StorageManager();
        } catch (DukeException e) {
            return;
        }

        this.taskManager = new TaskManager(this.storageManager.getTaskStorage());
        this.parserManager = new ParserManager();
    }
    /**
     * Retrieves the command for the given command string.
     *
     * @param fullCommand The given command string
     * @return Status string of the command execution
     */
    public String handleCommand(String fullCommand) {
        try {
            // Receive the command entered by the user
            Command command = this.parserManager.parseCommand(fullCommand);
            return command.execute(this.taskManager);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
