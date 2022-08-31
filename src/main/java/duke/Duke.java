package duke;

import duke.command.Command;
import duke.common.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;


/**
 * Duke, a Personal Assistant Chatbot that helps a person to keep track of various things.
 *
 * @author Rama Aryasuta Pangestu
 */
public class Duke {
    private final Ui ui;
    private final Storage storage;
    private TaskList taskList;

    /**
     * Constructs an instance of Duke.
     *
     * @param filePath the file location for saving data
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            taskList = new TaskList(storage.load());
        } catch (DukeException exception) {
            ui.addError(exception.getMessage());
            taskList = new TaskList();
        }
    }

    /**
     * Returns the response from Duke after executing a user command.
     *
     * @param fullCommand the user command
     * @return the response from Duke after executing a user command
     */
    public String getResponse(String fullCommand) {
        boolean isExit = false;
        try {
            Command c = Parser.parse(fullCommand);
            c.execute(ui, storage, taskList);
            isExit = c.isExit();
        } catch (DukeException e) {
            ui.addError(e.getMessage());
        }
        if (isExit) {
            System.exit(0);
        }
        return ui.getOutput();
    }
}
