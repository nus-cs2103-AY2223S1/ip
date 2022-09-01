package duke;

import duke.command.Command;
import duke.gui.Ui;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;

/**
 * Contains the methods and attributes necessary for Duke.
 */
public class Duke {

    private static TaskList tasks;
    private static Storage storage;
    private Ui ui;

    /**
     * Default public constructor which stores the saved list in /data/duke.txt.
     */
    public Duke() {
        this("data/duke.txt");
    }

    /**
     * Creates new TaskList, Storage and Ui for Duke to run.
     * @param filePath the path of the file the user wishes to store and write data to
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (Exception e) {
            tasks = new TaskList();
            ui.showLoadingError();
        }
    }

    /**
     * Processes the information typed by the user, and replies accordingly.
     *
     * @param userInput the text inputted by the user
     * @return the reply in response to the input
     */
    public String getResponse(String userInput) {
        Command command = Parser.parse(userInput);
        return command.runCommand(ui, storage, tasks);
    }
}
