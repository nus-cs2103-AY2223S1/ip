package duke.command;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

import java.io.FileNotFoundException;
import java.io.IOException;

import static java.lang.Integer.parseInt;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    private Parser parser;

    public Duke(String filePath) throws IOException {
        ui = new Ui();
        storage = new Storage(filePath);
    }

   // private static ArrayList<Task> tasks = new ArrayList<>();

    public void run() throws DukeException, IOException {
        ui.showGreetings();
        tasks = new TaskList(storage.loadFile());
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand, tasks);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            }
        }
    }
    public static void main(String[] args) throws DukeException, IOException, FileNotFoundException {
        new Duke("data/duke.txt").run();
    }
}
