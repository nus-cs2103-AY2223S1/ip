package duke;

import duke.command.Command;
import duke.exceptions.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Creates main Duke class that runs main program
 */
public class Duke {

    private static final String DIRECTORY = "./data/";
    private static final String FILENAME = "todo.txt";

    private Ui ui;
    private Storage storage;
    private TaskList tasks;


    /**
     * Creates new Duke object, initialising tasks array.
     *
     * @param directory Directory for save file
     * @param fileName  File name for save file
     */
    Duke(String directory, String fileName) {
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

    private void run() {
        ui.showWelcome();
        boolean isExit = false;

        while (!isExit) {
            try {
                ui.showPrompt();
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.getExit();
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
        duke.run();
    }
}
