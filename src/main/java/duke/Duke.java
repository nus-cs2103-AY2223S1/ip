package duke;

import javafx.application.Application;

import duke.command.Command;
import duke.gui.Main;

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
     * Create a new Duke application with the default save file path.
     */
    public Duke() {
        this(SAVE_FILE_PATH);
    }

    /**
     * Run the Duke application in CLI mode.
     */
    public void runCli() {
        ui.greet();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                ui.wrapPrint(c.execute(tasks, ui, storage));
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.wrapPrint(ui.showError(e));
            }
        }
    }

    /**
     * Executes a single command
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(tasks, ui, storage);
        } catch (DukeException e) {
            return ui.showError(e);
        }
    }

    /**
     * Entry point for the Duke application.
     * 
     * @param args command line arguments
     */
    public static void main(String[] args) {
        if (args.length > 0 && args[0].equals("-cli")) {
            new Duke().runCli();
        } else {
            Application.launch(Main.class, args);
        }
    }
}
