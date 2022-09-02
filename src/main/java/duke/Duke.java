package duke;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.CommandType;
import duke.Parser;

/**
 * Represents an instance of Duke.
 */
public class Duke {

    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    public Duke(String filePath) {
        this.ui = new Ui();
        this.ui.showWelcomeMessage();
        this.storage = new Storage(filePath);
        this.taskList = storage.readFromDisk();
    }

    /**
     * Continuously accepts user commands until "bye" is entered.
     */
    public void run() {

        String userCommand;
        Parser parser = new Parser(this.taskList);

        while (true) {
            userCommand = ui.getUserCommand();
            CommandType commandType = parser.getCommandType(userCommand);

            if (commandType == CommandType.EXIT) {
                this.ui.showGoodbyeMessage();
                storage.saveToDisk(taskList);
                break;
            } else if (commandType == CommandType.NONSENSE) {
                this.ui.showInvalidCommandError();
            } else {
                parser.execute(userCommand);
            }
        }
    }

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }
}