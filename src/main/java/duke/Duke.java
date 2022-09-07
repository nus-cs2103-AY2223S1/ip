package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.utils.Parser;
import duke.utils.Storage;
import duke.utils.TaskList;
import duke.utils.UI;

/**
 * duke.Duke program to create and save user's task list.
 */
public class Duke {
    private Storage storage = new Storage();
    private UI ui = new UI();
    private Parser parser = new Parser();
    private TaskList taskList = new TaskList(storage.read());

    /**
     * duke.gui.Main entry function.
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
    }

    /**
     * Gets the parsed response based on the user's input.
     *
     * @param input The input from the user.
     * @return The output of Duke, from the parser.
     */
    public String getResponse(String input) {
        try {
            Command command = parser.parse(ui.readCommand(input));
            return command.execute(storage, ui, taskList);
        } catch (DukeException e) {
            return ui.showError(e);
        }
    }

}
