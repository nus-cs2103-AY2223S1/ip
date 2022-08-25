package duke;

import duke.commands.Command;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.List;
import duke.ui.Ui;

public class Duke {
    private Storage storage;
    private List tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        try {
            storage = new Storage(filePath);
        } catch (DukeException e) {
            ui.showErrorMessage(e.getMessage());
        }
        try {
            tasks = new List();
            storage.load(tasks);
        } catch (DukeException e) {
            ui.showLoadingError();
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        new Duke("Duke/Duke.txt").run();
    }

    public void run() {
        ui.showWelcomeMessage();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.getUserCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showErrorMessage(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

}
