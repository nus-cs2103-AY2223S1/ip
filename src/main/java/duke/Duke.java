package duke;

import duke.command.Command;
import duke.parser.Parser;

/**
 * The Duke class represent a chatbot called Duke that is able to take in user inputs
 * and save these input in a storage.
 */
public class Duke {
    private final Storage storage;
    private TaskList taskList;
    private final Ui ui;
    private final Parser parser;

    /**
     * Initializes a Duke chatbot.
     * The filePath argument specify the file to be retrieved or created as the task list database.
     *
     * @param filePath File path containing the saved task list.
     */
    public Duke(String filePath) {
        this.storage = new Storage(filePath);
        this.ui = new Ui();
        this.parser = new Parser();
        try {
            this.taskList = storage.load();
        } catch (DukeException e) {
            ui.printException(e);
        }

    }

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }

    /**
     * Starts the Duke chatbot.
     */
    public void run() {
        boolean terminateFlag = false;
        ui.printWelcome();

        while (!terminateFlag) {
            try {
                Command currentCommand = parser.parse(ui.getCommand());
                currentCommand.execute(taskList, ui, storage);
                terminateFlag = currentCommand.isTerminatorGetter();
            } catch (DukeException e) {
                ui.printException(e);
            }
        }
    }
}
