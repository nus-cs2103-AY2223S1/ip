package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * A Duke class that encapsulates the information of Duke
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private final Ui ui;

    /**
     * Constructs a Duke object
     */
    public Duke() {
        ui = new Ui();

        try {
            storage = new Storage();
            tasks = storage.load();
        } catch (DukeException e) {
            //TODO
            ui.showError(e.getMessage());
            tasks = new TaskList();
        }
    }

    /**
     * To get the response from Duke
     *
     * @param input User input
     * @return Response from Duke
     */
    public String getResponse(String input) {
        try {
            ui.resetResponse();
            Command c = Parser.parse(input);
            c.execute(tasks, storage, ui);
            return ui.getResponse();
        } catch (DukeException e) {
            ui.showError(e.getMessage());
            return ui.getResponse();
        }

    }
}
