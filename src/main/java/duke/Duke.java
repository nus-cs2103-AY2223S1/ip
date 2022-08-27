package duke;

import java.io.FileNotFoundException;
import java.util.Scanner;

import duke.command.Command;
import duke.exception.DukeException;
import duke.task.TaskList;

/**
 * Represents the Duke application.
 *
 * @author njxue
 * @version v0.1
 */
public class Duke {
    /** Ui object which interacts with the user. **/
    private final Ui ui;

    /** Storage object which loads and saves the list of tasks. **/
    private final Storage storage;

    /** TaskList object containing user's list of tasks. **/
    private TaskList tasks;

    /**
     * Creates a new Duke object.
     *
     * @param pathString Relative path to the file containing the list of tasks.
     */
    public Duke(String pathString) {
        ui = new Ui();
        storage = new Storage(pathString);
        ui.showIsLoading();
        try {
            tasks = new TaskList(storage.load());
            ui.showLoadingSuccess();
        } catch (FileNotFoundException fnfe) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Starts the Duke application.
     */
    public void start() {
        Scanner scanner = new Scanner(System.in);
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                ui.showPrompt();
                String fullCommand = scanner.nextLine();
                Command command = Parser.parse(fullCommand);
                command.execute(tasks, ui, storage);
                isExit = command.isExit();
            } catch (DukeException de) {
                ui.showError(de.getMessage());
            }
        }
    }

    /**
     * The main method. Entry point of the Duke application.
     *
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        Duke duke = new Duke("storage/tasks.txt");
        duke.start();
    }
}
