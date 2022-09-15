package duke;

import duke.commands.Command;
import duke.tasks.TaskList;
import java.io.IOException;

/*
 * Main class for Duke program. This takes care of Duke's main logic, including 
 * startup, teardown and reading and writing to the file.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    private static final String DEFAULT_FILE_PATH = "data/duke.txt";

    /*
     * Constructor for Duke class. This takes in the path of the file to be read
     * and written to.
     * 
     * @param filePath The path of the file to be read and written to.
     */
    public Duke(String filePath) {

        try {
            this.ui = new Ui();
            this.storage = new Storage(filePath);
            this.tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.print(ui.showError(e));
            this.tasks = new TaskList();
        } catch (IOException e) {
            ui.print(e.getMessage());
        }
    }

    /*
     * Constructor for Duke if no file path is specified.
     */
    public Duke() {
        this(DEFAULT_FILE_PATH);
    }

    /*
     * Run method for Duke program. This takes in the user's input and executes
     * the appropriate command by parsing and executing the input.
     * If input is invalid, an error message is printed instead and user is prompted
     * to give another input.
     */
    public void run() {
        ui.print(ui.showWelcome());
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.print(ui.showLine()); // show the divider line ("_______")
                Command c = Parser.parseCommand(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.print(ui.showError(e));
            } finally {
                ui.print(ui.showLine());
            }
        }
    }

    public String getResponse(String userInput) {
        String response = "";
        try {
            Command c = Parser.parseCommand(userInput);
            response += c.execute(tasks, ui, storage);
        } catch (DukeException e) {
            response = e.getMessage();
        }
        return response;
    }

    /*
     * Main method for Duke program. This initialises Duke with the file path and
     * runs the program.
     */
    public static void main(String[] args) {
        new Duke("./data/tasks.txt").run();
    }
}
