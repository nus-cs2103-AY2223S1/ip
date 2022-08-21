package duke;

import duke.commands.Command;
import duke.parser.Parser;
import duke.storage.StorageFile;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Entry point of the Duke application.
 * Initialises the application and starts the interaction with the user.
 */
public class Duke {

    private StorageFile storage;
    private TaskList taskList;
    private Ui ui;

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
        ui = new Ui();
        storage = new StorageFile(System.getProperty("user.home") + "/Desktop");
        taskList = new TaskList(storage.load());
        ui.showWelcome();
    }

    /**
     * Prints the exit message.
     * Unloads the Jansi library and exits.
     */
    private void exit() {
        ui.showExit();
        ui.unloadJansi();
        System.exit(0);
    }

    /** Reads the user command and executes it, until the user issues the exit command */
    public void runCommandLoop() {
        boolean isExit = false;
        do {
            try {
                final String userCommand = ui.getUserCommand();
                final Command command = new Parser().parseCommand(userCommand);
                command.execute(taskList, ui, storage);
                isExit = command.isExit();
            } catch (DukeException e) {
                ui.showMessages(e.getMessage());
            }
        } while (!isExit);
    }

}
