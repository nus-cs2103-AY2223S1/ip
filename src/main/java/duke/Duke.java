package duke;

import duke.command.Command;

import duke.exception.DukeException;

import duke.util.Parser;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;


/**
 * Duke is a personal assistant chatbot.
 * It helps a person to keep track of various things.
 *
 * @author Rexong
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = storage.setUp();
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e);
            }
        }
    }

}




















