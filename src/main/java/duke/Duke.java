package duke;

import duke.command.Command;

/**
 * Simple CLI chatbot that reacts on user input.
 */
public class Duke {
    private static final String STORAGE_PATH = "data/duke.txt";

    private Ui ui;
    private Storage storage;
    private TaskList taskList;

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

    public void run() {
        ui.showWelcome();
        this.listenForInputs();
    }

    /**
     * Main function for the chatbot.
     * 
     * @param args
     *            System arguments. Not used for this program.
     */

    public static void main(String[] args) {
        new Duke(STORAGE_PATH).run();
    }
}
