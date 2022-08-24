package duke;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Duke {

    private Ui ui;
    private Storage storage;
    private TaskList tasks;

    public Duke(String filePath) throws FileNotFoundException, DukeException {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load());
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() throws IOException {
        ui.showWelcome();
        boolean stopRunning = false;
        while (!stopRunning) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Parser.parse(fullCommand, tasks, ui, storage);
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) throws IOException, DukeException {
        Duke duke = new Duke("text-ui-test/data/Duke.txt");
        duke.run();
    }
}
