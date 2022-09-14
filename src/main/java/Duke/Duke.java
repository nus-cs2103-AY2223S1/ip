package Duke;

import Duke.Exception.DukeException;
import Duke.Parser.Parser;
import Duke.Storage.Storage;
import Duke.TaskList.TaskList;
import Duke.UI.Ui;

/**
 * The launcher for the Duke application.
 */
public class Duke {
    private TaskList taskList;
    private final Storage storage;
    private final Ui ui;
    private final Parser parser;

    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.taskList = new TaskList(storage.loadFile());
        } catch (DukeException de) {
            ui.printException(de);
            this.taskList = new TaskList();
        }
        this.parser = new Parser(this.ui, this.taskList);
    }

    private void run() {
        try {
            this.ui.printWelcome();
            while (this.parser.hasNext()) {
                this.parser.handleNext();
            }
            this.storage.storeToFile(this.taskList);
        } catch (DukeException de) {
            this.ui.printException(de);
        }
        this.ui.printGoodbye();
    }

    public static void main(String[] args) {
        new Duke("Duke.txt").run();
    }
}
