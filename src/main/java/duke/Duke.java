package duke;

import java.util.ArrayList;
import java.nio.file.Paths;
import java.io.IOException;

/**
 * A program that manages and stores tasks inputted by a user
 */
public class Duke {

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
        storage = new Storage(Paths.get(filePath));
        try {
            tasks = new TaskList(storage.loadTasks(), storage);
            ui.printLoadingSuccessMessage();
        } catch (IOException err) {
            ui.printLoadingError(filePath);
            tasks = new TaskList(new ArrayList<>(), storage);
        }
    }

    /**
     * Runs the Duke program
     */
    public void run() {
        ui.printWelcomeMessage();
        String response;
        while (!this.tasks.getTaskListStatus()) {
            try {
                String inputCommand = ui.readInput();
                response = Parser.parse(inputCommand, tasks);
            } catch (DukeException err) {
                ui.printError(err.getMessage());
            } catch (IOException err) {
                ui.printError("\n:( OOPS! I can't refresh the task file!");
            }
        }
        ui.printExitMessage();
    }

    public String getWelcomeMessage() {
        return "Hello, my name is Duke!\nHow can I help you today?";
    }

    public String getExitMessage() {
        return "Goodbye! Looking forward to see you again soon!";
    }

    public String getResponse(String input) throws DukeException, IOException {
        String response = null;
        try {
            response = Parser.parse(input, tasks);
        } catch (DukeException err) {
            response = err.getMessage();
        }
        if (tasks.getTaskListStatus()) {
            response = getExitMessage();
        }
        return response;
    }

    public static void main(String[] args) {
        new Duke(FILE_NAME).run();
    }
}
