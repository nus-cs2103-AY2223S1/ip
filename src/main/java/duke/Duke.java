package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * Apollo is a chatbot that keeps tracks of various items, encapsulated
 * as a Duke instance.
 *
 * @author Kartikeya
 */
public class Duke {
    private Storage storage;
    private TaskList itemList;
    private final Ui ui;

    /**
     * Constructor for an instance of Apollo.
     * Initialises storage, itemList, ui and parser.
     */
    public Duke() {
        try {
            storage = new Storage();
            itemList = new TaskList(storage.load());
        } catch (DukeException e) {
            itemList = new TaskList();
        } finally {
            ui = new Ui();
        }
    }

    /**
     * Runs Apollo. Waits for input lines and processes them accordingly.
     */
    private void run() {
        ui.showIntro();
        while (true) {
            try {
                Command c = Parser.parseUserInput(ui.getUserInput());
                c.execute(itemList, ui, storage);
            } catch (DukeException e) {
                ui.showError(e);
            }
        }
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
