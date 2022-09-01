package duke;

import java.io.FileNotFoundException;
import java.io.IOException;

import duke.command.Command;
import duke.exception.DukeException;
import duke.task.TaskList;
import duke.util.Parser;
import duke.util.Storage;
import duke.util.Ui;

/**
 * An interactive ChatBot that provides functions of a to-do list
 * depending on the user's input.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor to create an instance of Duke.
     *
     * @param filePath String representation of the relative file path to store the data
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            ui.showError(e.getMessage());
            tasks = new TaskList();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Kickstarts the entire ChatBot.
     */
    public void run() {
        ui.displayWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException | IOException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Duke("./data/tasks.txt").run();
    }
}
