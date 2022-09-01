package duke;

import commands.Command;
import common.ChatResponse;
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

    public Duke() {
        initialize();
    }

    /**
     * Driver code that starts Duke.
     *
     * @param args Accepts any String arguments.
     */
    @Deprecated
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.initialize();
        duke.runLoop();
        duke.shutdown();
    }

    protected String getResponse(String input) {
        String msg = "";
        try {
            Command c = Parser.parseInput(input);
            msg += c.execute(this.taskList);
            this.storage.writeToStorage(this.taskList);
            return msg;
        } catch (DukeException e) {
            return e.toString();
        }
    }

    /**
     * Initializes Duke by attempting to load from storage (or creating storage if it does not exist),
     * and print relevant items to terminal.
     */
    public String initialize() {
        String msg = "";
        this.storage = new Storage(storageName, storageDirName);
        try {
            msg += this.storage.initializeStorage();
            this.storage.readFromStorage(this.taskList);
        } catch (IOException e) {
            this.taskList = new TaskList();
            return ChatResponse.returnChatError(e);
        }
        msg += ChatResponse.chatShowWelcome();
        return msg;
    }

    /**
     * Shuts down Duke by saving task list to storage, and printing out relevant items to terminal.
     */
    @Deprecated
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
    @Deprecated
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
