package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.ui.Ui;
import duke.util.TaskList;

public class Duke {
    private Storage storage;
    private TaskList tasklist;
    private Ui ui;

    // Constructor
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasklist = new TaskList(storage.load());
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            tasklist = new TaskList();
        }
    }

    /**
     * Main run method for the program.
     */
    private void run() {
        ui.showWelcome();
        while (true) {
            System.out.print(">> ");
            try {
                String fullCommand = ui.readCommand();
                Command cmd = Parser.parse(fullCommand);
                cmd.execute(storage, tasklist, ui);
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    // Main Method
    public static void main(String[] args) {
        Duke dk = new Duke("data/duke.txt");
        dk.run();
    }
}