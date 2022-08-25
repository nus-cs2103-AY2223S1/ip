package ted;

import ted.command.*;
import ted.exception.TedException;
import ted.parser.Parser;
import ted.storage.Storage;
import ted.task.*;
import ted.ui.Ui;

public class Ted {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Ted(String filePath, String fileName) {
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
        new Ted("data/ted.txt", "ted.txt").run();
    }
}
