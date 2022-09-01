package duke;

import duke.command.Command;

/*
 * Duke is a chatbot that helps you keep track of your tasks.
 * This is the main application class for Duke.
 */
public class Duke {
    private static final String SAVE_FILE_PATH = "data/tasks.txt";

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Create a new Duke application.
     * 
     * @param filePath path to the save file
     */
    public Duke(String filePath) {
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            System.out.println("Error: " + e.getMessage());
        }
        ui = new Ui();
    }

    /**
     * Run the Duke application.
     */
    public void run() {
        ui.greet();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                ui.wrapPrint(c.execute(tasks, ui, storage));
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e);
            }
        }
    }

    /**
     * Entry point for the Duke application.
     * 
     * @param args command line arguments
     */
    public static void main(String[] args) {
        new Duke(SAVE_FILE_PATH).run();
    }
}
