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
    private final Ui UI;

    /** Storage object which loads and saves the list of tasks. **/
    private final Storage STORAGE;
    
    /** TaskList object containing user's list of tasks. **/
    private TaskList tasks;

    /**
     * Creates a new Duke object.
     * 
     * @param pathString Relative path to the file containing the list of tasks.
     */
    public Duke(String pathString) {
        UI = new Ui();
        STORAGE = new Storage(pathString);
        UI.showIsLoading();
        try {
            tasks = new TaskList(STORAGE.load());
            UI.showLoadingSuccess();
        } catch (FileNotFoundException fnfe) {
            UI.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Starts the Duke application.
     */
    public void start() {
        Scanner scanner = new Scanner(System.in);
        UI.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                UI.showPrompt();
                String fullCommand = scanner.nextLine();
                Command command = Parser.parse(fullCommand);
                command.execute(tasks, UI, STORAGE);
                isExit = command.isExit();
            } catch (DukeException de) {
                UI.showError(de.getMessage());
            }
        }
    }
    
    public static void main(String[] args) {
        Duke duke = new Duke("storage/tasks.txt");
        duke.start();
    }
}
