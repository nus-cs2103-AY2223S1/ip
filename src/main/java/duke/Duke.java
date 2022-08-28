package duke;

import java.io.IOException;

/**
 * Main class
 * @author Ashiqur Rahman A0230107Y
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor that creates Duke object
     * @param filePath Location where the stored data of tasks is.
     */
    public Duke(String filePath) {
        ui = new Ui();
        ui.greeting();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() throws IOException, DukeException {
        Parser.run(tasks);
    }

    public static void main(String[] args) throws IOException, DukeException {
        new Duke("data/duke.txt").run();
    }
}
