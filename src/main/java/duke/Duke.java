package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

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
     * @param input User input
     * @return Response from Duke
     */
    public String getResponse(String input) {
        boolean isExit = false;
        while (!isExit) {
            try {
                ui.resetResponse();
                Command c = Parser.parse(input);
                c.execute(tasks, storage, ui);
                isExit = c.bye();
                return ui.getResponse();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
                return ui.getResponse();
            }
        }
        return "";
    }
}
