package ted;

import ted.command.*;
import ted.exception.TedException;
import ted.parser.Parser;
import ted.storage.Storage;
import ted.task.*;
import ted.ui.Ui;

/**
 * Represents a task bot called Ted. A <code>Ted</code> object interacts with user
 * and keeps track of user's tasks.
 */
public class Ted {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Creates Ted object to interact with user and keep track of tasks.
     *
     * @param filePath path to file with saved task list data.
     * @param fileName name of file with saved task list data.
     */
    public Ted(String filePath, String fileName) {
        storage = new Storage(filePath, fileName);
        tasks = storage.loadFile();
        ui = new Ui();
    }

    /**
     * Runs the task bot.
     */
    public void run() {
        ui.welcomeUser();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (TedException e) {
                ui.tedResponse(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Ted("data/ted.txt", "ted.txt").run();
    }
}
