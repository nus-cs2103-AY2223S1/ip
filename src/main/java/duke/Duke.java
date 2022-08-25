package duke;

import duke.command.*;
import duke.exception.TedException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.*;
import duke.ui.Ui;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath, String fileName) {
        storage = new Storage(filePath, fileName);
        tasks = storage.loadFile();
        ui = new Ui();
    }

    public void run() {
        ui.welcomeUser();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (TedException e) {
                ui.tedResponse(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Duke("data/ted.txt", "ted.txt").run();
    }
}
