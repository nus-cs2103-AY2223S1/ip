package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.task.TaskList;

import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Represents the Duke application.
 *
 * @author njxue
 * @version v0.1
 */
public class Duke {
    /** <code>Ui</code> object which interacts with the user. **/
    private final Ui ui;

    /** <code>Storage</code> object which loads and saves the list of tasks. **/
    private final Storage storage;
    
    /** <code>TaskList</code> object containing user's list of tasks. **/
    private TaskList tasks;

    /**
     * Creates a new <code>Duke</code> object.
     * 
     * @param pathString Relative path to the file containing the list of tasks.
     */
    public Duke(String pathString) {
        this.ui = new Ui();
        this.storage = new Storage(pathString);
        ui.showIsLoading();
        try {
            this.tasks = new TaskList(storage.load());
            ui.showLoadingSuccess();
        } catch(FileNotFoundException fnfe) {
            ui.showLoadingError();
            this.tasks = new TaskList();
        }
    }

    /**
     * Starts the Duke application.
     */
    public void start() {
        Scanner scanner = new Scanner(System.in);
        this.ui.showWelcome();

        boolean isExit = false;

        while (!isExit) {
            try {
                this.ui.showPrompt();
                String fullCommand = scanner.nextLine();
                Command command = Parser.parse(fullCommand);
                command.execute(this.tasks, this.ui, this.storage);
                isExit = command.isExit();
            } catch (DukeException de) {
                ui.showError(de.getMessage());
            }
        }
    }
    
    public static void main(String[] args) {
        Duke duke = new Duke("storage/tasks.txt");
        duke.start();
    }
}