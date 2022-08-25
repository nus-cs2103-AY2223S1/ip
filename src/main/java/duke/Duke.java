package duke;

import duke.commands.Command;
import duke.others.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.storage.TaskList;
import duke.ui.Ui;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

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

    public static void main(String[] args) {
        Duke duke = new Duke("data/duke.txt");
        duke.run();
    }

    public void run() {

        ui.showWelcome();
        boolean isRunning = true;

        while (isRunning) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isRunning = c.isRunning();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

}
