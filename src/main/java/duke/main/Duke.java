package duke.main;

import duke.command.Command;
import duke.errors.DukeException;
import duke.task.TaskList;

public class Duke {
    public static final String FILEPATH = "data/duke.txt";
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
                ui.showError(e.getMessage());
            }
        }
        ui.showBye();
    };

    public static void main(String[] args) {
        new Duke(FILEPATH).run();
    }
}
