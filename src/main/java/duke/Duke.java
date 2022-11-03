package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Duke, a cli app that helps users to track their tasks.
 *
 * @author Leong Jia Hao Daniel
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs Duke.
     *
     * @param filePath The filepath where the data that duke
     *                 uses is stored.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Returns the response from duke to the gui.
     *
     * @param input The input from the gui.
     * @return The expected output to the user.
     */
    public String getResponse(String input) {
        try {
            Command command = Parser.parse(input);
            return command.execute(ui, storage, tasks);
        } catch (DukeException e) {
            return ui.showError(e);
        }
    }

    /**
     * Returns the welcome message.
     *
     * @return The welcome message to the user.
     */
    public String getWelcomeMessage() {
        return ui.showWelcome();
    }
}
