package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Duke, a chatbot that helps you keep track of the tasks you have.
 *
 * @author Bryan Ng Zi Hao
 */
public class Duke {
    private Ui ui;
    private Storage storage;
    private TaskList taskList;

    /**
     * Constructor for Duke.
     *
     * @param filePath The location where the data is stored for Duke.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            taskList = new TaskList(storage.loadFile());
        } catch (DukeException e) {
            ui.formatMessage("Unable to load file.");
            taskList = new TaskList();
        }
    }

    /**
     * Greeting for Duke.
     *
     * @return The greeting for Duke.
     */
    public String getGreeting() {
        return Ui.greet();
    }

    /**
     * Reply the user based on what the input is.
     *
     * @return the String representation of the reply.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            storage.writeFile(taskList.getTasks());
            return c.execute(ui, storage, taskList);
        } catch (DukeException e) {
            return ui.formatMessage(String.valueOf(e));
        }
    }
}
