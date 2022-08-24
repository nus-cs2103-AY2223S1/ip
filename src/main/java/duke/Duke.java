package duke;

import duke.commands.Command;
import duke.data.TaskList;
import duke.data.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * Represents the Duke chatbot.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor for a Duke.
     */
    public Duke() {
        this.ui = new Ui();
        this.storage = new Storage();
        try {
            this.tasks = new TaskList(this.storage.load());
        } catch (DukeException e) {
            this.ui.show(e.getMessage());
            this.tasks = new TaskList();
        }
    }

    /**
     * Runs the Duke chatbot.
     */
    public void run() {
        this.ui.showGreeting();
        boolean isBye = false;
        while (!isBye) {
            try {
                String input = this.ui.readInput();
                Command command = Parser.parse(input);
                command.execute(this.tasks, this.ui, this.storage);
                isBye = command.isBye();
            } catch (DukeException e) {
                this.ui.show(e.getMessage());
            }
        }
    }

    /**
     * Initialises and runs the Duke chatbot.
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }
}
