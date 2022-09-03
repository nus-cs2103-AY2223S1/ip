package duke.main;

import java.io.File;
import java.io.IOException;

import duke.command.Command;
import duke.exception.DukeException;

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

    /**
     * Initializes the chatbot and the file to store its data.
     *
     * @param args arguments.
     * @throws IOException i the ile does not exist.
     */
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

    /**
     * Starts the chatbot.
     */
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
