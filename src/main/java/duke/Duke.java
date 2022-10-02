package duke;

import duke.command.Command;
import duke.exceptions.DukeException;
import duke.launcher.Launcher;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Creates main Duke class that runs main program
 */
public class Duke {

    private static final String DIRECTORY = "./data/";
    private static final String FILENAME = "tasks.txt";
    private static final boolean isGui = true;
    private final Ui ui;
    private Storage storage;
    private TaskList tasks;


    /**
     * Creates new Duke object, initialising tasks array.
     *
     * @param directory Directory for save file
     * @param fileName  File name for save file
     */
    public Duke(String directory, String fileName) {
        ui = new Ui();
        try {
            storage = new Storage(directory, fileName);
        } catch (DukeException e) {
            ui.showError(e.getMessage());
        }
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showError(e.getMessage());
            ui.showLoadingError();
        }
    }

    /**
     * Initialises Duke class with UI, Storage and TaskList initialization
     */
    public Duke() {
        ui = new Ui();
        try {
            storage = new Storage(DIRECTORY, FILENAME);
        } catch (DukeException e) {
            ui.showError(e.getMessage());
        }
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showError(e.getMessage());
            ui.showLoadingError();
        }
    }

    private void run() {
        ui.showWelcome();
        boolean isExit = false;

        while (!isExit) {
            try {
                ui.showPrompt();
                String fullCommand = ui.readCommand();
                Command command = Parser.parse(fullCommand);
                command.execute(tasks, ui, storage);
                isExit = command.getExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            }
        }

        ui.showExit();
    }

    /**
     * Initialises Duke class and runs the main part.
     *
     * @param args
     */
    public static void main(String[] args) {
        Duke duke = new Duke(DIRECTORY, FILENAME);

        if (isGui) {
            Launcher.main(args);
        } else {
            duke.run();
        }
    }

    public String getResponse(String input) {
        try {
            Command command = Parser.parse(input);
            return command.execute(tasks, ui, storage);
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
