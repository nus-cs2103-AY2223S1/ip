package duke;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Duke bot object class, which has a task list, a storage space and a user interface.
 */
public class Duke {

    private Ui ui;
    private Storage storage;
    private TaskList tasks;

    /**
     * Creates a Duke object with the given filepath.
     *
     * @param filePath The path of the file where the list of tasks is stored in a .txt file.
     */
    public Duke(String filePath) throws FileNotFoundException, DukeException {
        ui = new Ui();
        storage = new Storage(filePath);
        TaskList currentTasks;
        try {
            currentTasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            currentTasks = new TaskList();
        }
        this.tasks = currentTasks;
    }

    /**
     * Prints out Duke's responses to the console line by line.
     *
     * @throws IOException If the filepath is incorrect.
     */
    public void run() throws IOException {
        ui.showWelcome();
        boolean stopRunning = false;
        while (!stopRunning) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Parser.parse(fullCommand, tasks, ui, storage);
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * Runs the main Duke program.
     *
     * @param args Main execution of program.
     * @throws IOException   If the filepath is incorrect.
     * @throws DukeException If the line in file contains incorrect format.
     */
    public static void main(String[] args) throws IOException, DukeException {
        Duke duke = new Duke("data/tasks.txt");
        duke.run();
    }
}
