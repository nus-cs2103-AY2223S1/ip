package ted;

import ted.command.Command;
import ted.exception.TedException;
import ted.parser.Parser;
import ted.storage.Storage;
import ted.task.TaskList;
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
     * Returns bot response to user input.
     *
     * @param input user input.
     * @return bot response.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(tasks, ui, storage);
        } catch (TedException e) {
            return e.getMessage();
        }
    }

    /**
     * Returns a welcome message to the user.
     *
     * @return welcome message string.
     */
    public static String getWelcome() {
        return "Hello! I'm Ted and I'm here to help you keep track of your tasks |._.|\n"
                + "How can I assist you today?\n";
    }
}
