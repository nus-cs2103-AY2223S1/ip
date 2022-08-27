package duke;

import duke.commands.Command;
import duke.commands.CommandResult;
import duke.parser.Parser;
import duke.storage.StorageFile;
import duke.task.TaskList;
import duke.ui.TextUi;

/**
 * Entry point of the Duke application.
 * Initialises the application and starts the interaction with the user.
 */
public class Duke {

    private StorageFile storage;
    private TaskList taskList;
    private TextUi textUi;

    public static void main(String[] args) {
        new Duke().run();
    }

    /** Runs the program until termination */
    public void run() {
        start();
        runCommandLoop();
        exit();
    }

    /**
     * Sets up the required objects, loads the data from the storage file.
     * Prints the welcome message.
     */
    private void start() {
        textUi = new TextUi();
        storage = new StorageFile(System.getProperty("user.home") + "/Desktop");
        taskList = new TaskList(storage.load());
        textUi.showWelcome();
    }

    /**
     * Prints the exit message.
     * Unloads the Jansi library and exits.
     */
    private void exit() {
        textUi.showExit();
        textUi.unloadJansi();
        System.exit(0);
    }

    /** Reads the user command and executes it, until the user issues the exit command */
    public void runCommandLoop() {
        boolean isExit = false;
        do {
            final String userCommand = textUi.getUserCommand();
            final Command command = new Parser().parseCommand(userCommand);
            CommandResult result = command.execute(taskList, textUi, storage);
            textUi.showResultToUser(result);
            isExit = command.isExit();
        } while (!isExit);
    }

}
