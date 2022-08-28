package duke;

import java.io.IOException;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

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
