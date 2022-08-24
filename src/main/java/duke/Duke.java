package duke;

import commands.Command;
import common.Parser;
import common.Storage;
import common.Ui;
import dukeexceptions.DukeException;
import tasklist.TaskList;

import java.io.IOException;

/**
 * Duke is a CLI-program that allows users to perform CRUD operations
 * on user-defined tasks. These are saved to an external storage (txt file).
 */
public class Duke {
    static final String storageName = "storage.txt";
    static final String storageDirName = "data";

    private Storage storage;
    private TaskList taskList = new TaskList();

    /**
     * Driver code that starts Duke.
     *
     * @param args Accepts any String arguments.
     */
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.initialize();
        duke.runLoop();
        duke.shutdown();
    }

    /**
     * Initializes Duke by attempting to load from storage (or creating storage if it does not exist),
     * and print relevant items to terminal.
     */
    public void initialize() {
        this.storage = new Storage(storageName, storageDirName);
        try {
            this.storage.initializeStorage();
            this.storage.readFromStorage(this.taskList);
        } catch (IOException e) {
            Ui.printError(e);
            this.taskList = new TaskList();
        }
        Ui.showWelcome();
    }

    /**
     * Shuts down Duke by saving task list to storage, and printing out relevant items to terminal.
     */
    public void shutdown() {
        this.storage.writeToStorage(this.taskList);
        Ui.printSaving();
        Ui.printGoodbye();
        Ui.printDivider();
    }

    /**
     * Loop that is called to receive and process user input.
     * This loop continues to run until the exit signal is flagged as true.
     */
    public void runLoop() {
        boolean isExit = false;
        while (!isExit) {
            try {
                Ui.printGetUserCommand();
                String fullCommand = Ui.readUserCommand();
                Command c = Parser.parseInput(fullCommand);
                c.execute(this.taskList);
                isExit = c.isExit();
            } catch (DukeException e) {
                Ui.printError(e);
            } finally {
                Ui.printDivider();
            }
        }
    }
}
