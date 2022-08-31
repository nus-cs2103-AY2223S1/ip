package seedu.duke;

import seedu.duke.command.Command;
import seedu.duke.operations.Parser;
import seedu.duke.operations.Storage;
import seedu.duke.operations.TaskList;
import seedu.duke.operations.Ui;
import seedu.duke.task.Task;

import java.util.ArrayList;

/**
 * Duke is the main class for the chatbot application. It is designed to be
 * able to take in inputs within command line, keep track of a list of tasks
 * the user had typed.
 *
 * Duke is able to take the following commands:
 * <ul>
 *     <li>list</li>
 *     <li>mark</li>
 *     <li>unmark</li>
 *     <li>delete</li>
 *     <li>bye</li>
 *     <li>todo</li>
 *     <li>deadline</li>
 *     <li>event</li>
 * </ul>>
 *
 * @author Su Peigeng
 */
public class Duke {
    private final TaskList TASKS;
    private final Storage STORAGE;
    private final Ui UI;

    /**
     * Constructor for Duke instance
     *
     * @param filename      Pathname to the file where the task-list is stored
     */
    Duke(String filename) {
        this.ui = new Ui();
        this.storage = new Storage(filename);
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            tasks = storage.load();
        } catch (DukeException e) {
            ui.showLoadingError();
        }
        this.tasks = new TaskList(tasks);
    }

    /**
     * Runs Duke's chat-bot functionality.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}
