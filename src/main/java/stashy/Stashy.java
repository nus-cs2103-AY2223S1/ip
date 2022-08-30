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
    private static final String DATA_FILEPATH = "src/main/data/tasks.txt";
    private static Ui ui;
    private static Storage storage;
    private static TaskList tasks;

    /**
     * Constructor method.
     */
    public Stashy() {
        ui = new Ui();
        storage = new Storage(DATA_FILEPATH);
        try {
            storage.create();
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
                Command c = Parser.parseCommand(fullCommand);
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
     * The main driver method of the chatbot.
     */
    public static void main(String[] args) {
        new Stashy().run();
    }
}
