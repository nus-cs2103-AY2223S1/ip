package duke;

import duke.command.Command;

/**
 * Simple CLI chatbot that reacts on user input.
 */
public class Duke {
    private static final String STORAGE_PATH = "data/duke.txt";

    private Ui ui;
    private StorageInterface storage;
    private TaskList taskList;

    /**
     * Constructor for Duke.
     * Initialises, Ui, TaskList and Storage used by the application.
     *
     * @param storagePath Filepath to use to store Tasks.
     */
    Duke(String storagePath) {
        this.ui = new Ui();
        this.storage = new Storage(storagePath);
        Command.setUi(this.ui);
        Command.setStorage(this.storage);
        try {
            this.taskList = new TaskList(this.storage.readFile());
            ui.formatAndPrint("Successfully loaded from storage file.");
        } catch (DukeException e) {
            ui.displayErrorMessage(e.getMessage());
            this.taskList = new TaskList();
        } finally {
            Command.setTaskList(this.taskList);
        }
    }

    /**
     * Loop that listens for user input and executes Commands corresponding to the
     * inputs.
     */
    void listenForInputs() {
        boolean isExit = false;
        while (!isExit) {
            try {
                String commandString = ui.readCommand();
                Command command = Parser.parse(commandString);
                command.execute();
                isExit = command.isExit();
            } catch (DukeException e) {
                ui.displayErrorMessage(e.getMessage());
            }
        }
    }

    /**
     * Shows welcome messge and start listening for user input.
     */
    public void run() {
        ui.showWelcome();
        this.listenForInputs();
    }

    /**
     * Main function for the chatbot.
     *
     * @param args System arguments. Not used for this program.
     */

    public static void main(String[] args) {
        new Duke(STORAGE_PATH).run();
    }
}
