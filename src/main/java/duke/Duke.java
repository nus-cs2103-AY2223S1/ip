package duke;

import duke.command.Command;
import duke.common.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Main class of the application
 *
 * @author Pontakorn Prasertsuk
 */
public class Duke {

    private static final String FILE_PATH = "data/tasks.txt";

    private final Ui ui;
    private final Storage storage;
    private TaskList taskList;

    /**
     * Constructs a new Duke instance.
     *
     * @param filePath the path to the file that stores the tasks
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            taskList = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showError(e.getMessage());
            taskList = new TaskList();
        }
    }

    /**
     * Runs the Duke application.
     */
    public void run() {
        ui.showGreeting();
        boolean isExit = false;
        while (!isExit) {
            ui.showInput();
            try {
                String fullCommand = Parser.readCommand();
                ui.showLine();
                Command command = Parser.parse(fullCommand);
                command.execute(taskList, ui, storage);
                isExit = command.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public String getResponse(String input) {
        try {
            Command command = Parser.parse(input);
            return command.execute(taskList, ui, storage);
        } catch (DukeException e) {
            return "Error: " + e.getMessage();
        }
    }

    public static void main(String[] args) {
        new Duke(FILE_PATH).run();
    }
}
