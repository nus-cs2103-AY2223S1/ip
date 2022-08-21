package duke.duke;
import duke.command.Command;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

import java.io.IOException;

/**
 * The class Duke that runs the program
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

     /**
      * Constructor for Duke that initializes Ui and storage
      *
      * @param filePath file path that storage uses to know location of Duke.txt
      */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
    }

     /**
      * Method run() that greets the user, loads tasklist from Duke.txt, and continues program until
      * isExit() returns false through one of the commands
      *
      * @throws IOException
      */
    public void run() throws IOException {
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

     /**
      * Creates new Duke instance and runs it
      *
      * @param args needed for main to run
      * @throws IOException
      */
    public static void main(String[] args) throws IOException {
        new Duke("data/duke.txt").run();
    }
}
