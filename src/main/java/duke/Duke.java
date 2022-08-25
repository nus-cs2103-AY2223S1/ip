package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.ui.Ui;
import duke.util.TaskList;

public class Duke {
    private final Storage storage;
    private final TaskList tasklist;
    private final Ui ui;

    // Constructor
    public Duke() {
        ui = new Ui();
        storage = new Storage();
        tasklist = new TaskList();

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
        Duke dk = new Duke();
        dk.run();
    }
}