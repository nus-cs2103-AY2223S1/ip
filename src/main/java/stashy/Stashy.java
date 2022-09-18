package stashy;

import stashy.commands.Command;
import stashy.data.exception.StashyException;
import stashy.data.task.TaskList;
import stashy.parser.Parser;
import stashy.storage.Storage;
import stashy.ui.Ui;

/**
 * Represents a chatbot that can do many things, just like
 * what a regular chatbot should be able to do.
 */
public class Stashy {
    /**
     * The absolute filepath to store the task list, the UI,
     * the storage to handle with files, and the task list itself.
     */
    private static final String DATA_FILEPATH = "src/main/data/tasks.txt";

    private static Ui ui;

    private static Storage storage;

    private static TaskList tasks;

    /**
     * Represents the constructor method.
     */
    public Stashy() {
        ui = new Ui();
        storage = new Storage(DATA_FILEPATH);
        try {
            storage.createFile();
            tasks = new TaskList(storage.load());
        } catch (StashyException se) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Runs the interactive chatbot.
     */
    public static void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parseCommand(fullCommand, false);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (StashyException se) {
                ui.showError(se.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * Executes a given command supplied to Stashy but returns a string.
     *
     * @param c The command passed to this method
     * @return The String version of the execution result
     */
    public String executeCommandReturnString(Command c) {
        try {
            return c.executeString(tasks, ui, storage);
        } catch (StashyException se) {
            return ui.showErrorString(se.getMessage());
        }
    }

    /**
     * Shows the GUI welcome message as a String.
     *
     * @return A simple welcome message.
     */
    public static String showWelcomeMessageGui() {
        return ui.showWelcomeString();
    }

    /**
     * Represents the main driver method of the chatbot.
     *
     * @param args Command-line arguments for the application
     */
    public static void main(String[] args) {
        new Stashy().run();
    }
}
