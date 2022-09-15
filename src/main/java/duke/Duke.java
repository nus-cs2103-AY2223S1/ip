package duke;

import static duke.utils.Ui.LOGO;

import java.io.File;

import duke.commands.CommandHandler;
import duke.commands.CommandHandlerFactory;
import duke.data.Storage;
import duke.exceptions.DukeException;
import duke.tasks.TaskList;
import duke.utils.Ui;

/**
 * Duke is a program that helps its user to store tasks.
 */
public class Duke {

    private final TaskList taskList;
    private final CommandHandlerFactory commandHandlerFactory;
    private boolean hasInitializingError = false;
    private String initializingErrorMessage = "";

    /**
     * Describes the constructor of Duke.
     */
    public Duke() {
        File storageDirectory = new File("./data");
        if (!storageDirectory.exists() && !storageDirectory.mkdir()) {
            hasInitializingError = true;
            initializingErrorMessage = "Could not create /duke.data directory";
        }

        Storage db = new Storage("./data/duke.txt");
        taskList = db.load();
        commandHandlerFactory = new CommandHandlerFactory();
    }

    /**
     * Checks if duke has terminated.
     * @return whether duke has terminated.
     */
    public boolean isClosed() {
        return taskList.isClosed();
    }

    /**
     * Gets the response to the user.
     * @param input User input.
     * @return Duke's response to the user.
     */
    public String getResponse(String input) {
        try {
            CommandHandler commandHandler = commandHandlerFactory.getHandler(input);
            return Ui.wrapWithLines(commandHandler.handle(taskList));
        } catch (DukeException e) {
            return Ui.printErrorMessage(e.getMessage());
        }
    }

    /**
     * Returns the initialization message of Duke.
     * @return the initialization message.
     */
    public String getInitializationMessage() {
        if (hasInitializingError) {
            return initializingErrorMessage;
        } else {
            return getWelcomeMessage();
        }
    }

    /**
     * Returns the welcome message when duke initialises.
     * @return the welcome message.
     */
    private String getWelcomeMessage() {
        return "Hello from\n" + LOGO;
    }

}
