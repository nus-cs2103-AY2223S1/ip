package duke;

import duke.command.Command;
import duke.parser.Parser;
import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class Duke {

    private Ui ui;
    private Storage storage;
    private TaskList tasks;

    public final static String DIRECTORY = "./data/";
    public final static String FILENAME = "todo.txt";


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