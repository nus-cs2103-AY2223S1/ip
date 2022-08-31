import java.io.IOException;

import yilia.Parser;
import yilia.Storage;
import yilia.Ui;
import yilia.command.Command;
import yilia.exception.DescriptionEmptyException;
import yilia.exception.KeywordMissingException;
import yilia.exception.NoIndexException;
import yilia.exception.TimeFormatException;
import yilia.exception.YiliaException;
import yilia.task.TaskList;

/**
 * Represents a chat box to complete given commands.
 */
class Yilia {
    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    public Yilia(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (IOException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Runs the main body of the chat box.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DescriptionEmptyException e) {
                ui.showError(e.getMessage());
            } catch (KeywordMissingException e) {
                ui.showError(e.getMessage());
            } catch (NoIndexException e) {
                ui.showError(e.getMessage());
            } catch (TimeFormatException e) {
                ui.showError(e.getMessage());
            } catch (YiliaException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }
    public static void main(String[] args) {
        new Yilia("data/yilia.txt").run();
    }
}
