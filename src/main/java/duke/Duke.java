package duke;

import java.util.ArrayList;
import java.nio.file.Paths;
import java.io.IOException;

/**
 * A program that manages and stores tasks inputted by a user
 */
public class Duke {

    private static final String EXIT_COMMAND = "bye";
    private static final String FILE_NAME = "dukeList.txt";
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * A constructor for the Duke class
     *
     * @param filePath Path of the file that stores the list of tasks
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(Paths.get(FILE_NAME));
        try {
            tasks = new TaskList(storage.loadTasks(), storage);
            ui.printLoadingSuccessMessage();
        } catch (IOException err) {
            ui.printLoadingError(FILE_NAME);
            tasks = new TaskList(new ArrayList<>(), storage);
        }
    }

    /**
     * Runs the Duke program
     */
    public void run() {
        ui.printWelcomeMessage();
        boolean isExit = false;
        while (!isExit) {
            try {
                String inputCommand = ui.readInput();
                isExit = Parser.parse(inputCommand, tasks, ui);
            } catch (DukeException err) {
                ui.printError(err.getMessage());
            } catch (IOException err) {
                ui.printError("\n:( OOPS! I can't refresh the task file!");
            }
        }
        ui.printExitMessage();
    }

    public static void main(String[] args) {
        new Duke(FILE_NAME).run();
    }
}
