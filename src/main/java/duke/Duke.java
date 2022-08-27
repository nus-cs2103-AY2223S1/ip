package duke;

import duke.command.Command;

import java.io.IOException;

/**
 * {@code Duke} is the main class of this program, and it manages the storage and the ui.
 *
 * @author ngshijun
 */
public class Duke {

    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;

    /**
     * Constructor for {@code Duke} to initialize the ui and the storage
     * @param filePath the path to where the file is located
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        if (storage.isFileExists()) {
            tasks = new TaskList(storage.load());
        } else {
            ui.showLoadingError();
            storage.createNewFile();
            tasks = new TaskList();
        }
    }

    /**
     * Run the program to start interacting with the user
     */
    public void run() {
        ui.greet();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = Command.isExit;
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }
    public static void main(String[] args) throws IOException {
        new Duke("tasks.dat").run();
    }
}
