package duke;

import duke.commands.Command;
import duke.exceptions.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Entry point of the Duke application.
 * Initializes the application and starts the interaction with the user.
 */
public class Duke {

    private static final String LINE = "    __________________________________________________________________________";
    private static final String INDENT = "     ";

    private boolean isClosed = false;
    private TaskList tasks = new TaskList();
    private int counter = 0;

    private Ui ui;
    private Storage storage;
    private Parser parser;


    /**
     * Initialises the Duke object. Initialises the fields ui, storage and parser.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage();
        parser = new Parser();
        try {
            tasks = new TaskList(storage.retrieveTasks());
        } catch (DukeException e) {
            ui.showErrorMessage(e.getMessage());
            tasks = new TaskList();
        }
    }

    /**
     * Sets up the required objects, loads up the data from the storage file, and prints the welcome message.
     *
     * @param args arguments supplied by the user at program launch
     */
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }

    private void run() {
        ui.showGreetingMessage();
        boolean isByeCommand = false;
        while (!isByeCommand) {
            try {
                String fullCommand = ui.readCommand();
                ui.drawLine(); // show the divider line ("_______")
                Command c = parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isByeCommand = c.isByeCommand();
            } catch (DukeException e) {
                ui.showErrorMessage(e.getMessage());
            } finally {
                ui.drawLine();
            }
        }
    }

}