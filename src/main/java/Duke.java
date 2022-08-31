import commands.Command;
import data.TaskList;
import exceptions.DukeException;
import parser.Parser;
import storage.Storage;
import ui.Ui;

public class Duke {
    private final Storage storage;
    private final Ui ui;
    private TaskList tasks;

    /**
     * Instantiates a Duke object with storage at some file path.
     * @param filePath File path for storage.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = storage.load();
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Instantiates the Duke program and runs it.
     */
    public static void main(String[] args) {
        new Duke("duke.dat").run();
    }

    /**
     * Initiates input/output for the Duke program.
     */
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
    }
}
