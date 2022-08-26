package duke.main;

import duke.command.Command;
import duke.exception.DukeException;

import java.io.IOException;
import java.io.File;

/**
 * Starts the duke chatbot.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Initializes the chatbot.
     *
     * @param filePath path to store all the tasks.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public static void main(String[] args) throws IOException {
        File dir = new File("data/");
        if (!dir.exists()) {
            dir.mkdir();
        }

        File saved = new File("data/duke.txt");
        if (!saved.exists()) {
            saved.createNewFile();
        }

        new Duke("data/duke.txt").runBot();
    }

    public void runBot() {

        try {
            storage.load();
        } catch (DukeException e) {
            ui.printMessage(e.getMessage());
        }

        ui.greetingMessage();

        boolean exitBot = false;
        while (!exitBot) {
            try {
                String fullCommand = ui.readCommand();
                ui.linePrint(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                exitBot = c.exitBot();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.linePrint();
            }
        }
    }
}
