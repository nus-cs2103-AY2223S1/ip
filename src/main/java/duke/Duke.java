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
    private Parser parser;

    public Duke(String filePath) {
        this.storage = new Storage(filePath);
        this.taskList = storage.readFromDisk();
        this.parser = new Parser(taskList);
    }

    String getResponse(String input) {
        return parser.execute(input);
    }

    public static void main(String[] args) {
        Launcher.main(args);
    }

    void saveTaskList() {
        storage.saveToDisk(this.taskList);
    }
}