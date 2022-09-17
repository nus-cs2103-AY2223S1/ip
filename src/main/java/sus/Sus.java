package sus;

import sus.commands.Command;
import sus.commands.CommandResult;
import sus.parser.Parser;
import sus.storage.StorageFile;
import sus.task.TaskList;
import sus.ui.TextUi;

/**
 * Entry point of the Duke application.
 * Initialises the application and starts the interaction with the user.
 */
public class Sus {

    private StorageFile storage;
    private TaskList taskList;
    private TextUi textUi;

    public static void main(String[] args) {
        new Sus().run();
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
        storage = new StorageFile();
        taskList = new TaskList(storage.load());
        textUi.showWelcome();
    }

    /**
     * Prints the exit message.
     * Unloads the Jansi library and exits.
     */
    private void exit() {
        textUi.showExit();
        System.exit(0);
    }

    /** Reads the user command and executes it, until the user issues the exit command */
    public void runCommandLoop() {
        boolean isExit;
        do {
            final String userCommand = textUi.getUserCommand();
            final Command command = new Parser().parseCommand(userCommand);
            CommandResult result = command.execute(taskList, textUi, storage);
            textUi.showResultToUser(result);
            isExit = command.isExit();
        } while (!isExit);
    }

}
