package duke;

import duke.commands.Command;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.TextUi;

/**
 * Main class of chatbot Duke.
 */
public class Duke {
    private Storage storage;
    private TaskList taskList;
    private TextUi ui;

    /**
     * Creates a new instance of Duke.
     */
    public Duke() {
        this.ui = new TextUi();
        this.storage = new Storage();
        try {
            this.taskList = new TaskList(storage.loadTasks());
        } catch (DukeException e) {
            this.ui.showLoadingError();
            this.taskList = new TaskList();
        }
    }

    /**
     * Runs the Duke until user exits.
     */
    public void run() {
        this.ui.showWelcomeMessage();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = this.ui.readUserInput();
                Command c = Parser.parse(fullCommand);
                c.execute(this.taskList, this.ui, this.storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                this.ui.printTextWithDivider(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
