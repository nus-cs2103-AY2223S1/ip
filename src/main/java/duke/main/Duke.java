package duke.main;

import duke.command.Command;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.ui.Ui;
import duke.task.TaskList;

public class Duke {

    // FILE PATH
    private static final String saveFilePath = "data/duke.txt";

    // INSTANCE VARIABLES
    private final TaskList tasks;
    private final Storage storage;

    public Duke() {
        this.storage = new Storage(saveFilePath);
        this.tasks = new TaskList(storage.load());
    }

    public String getResponse(String input) {
        try {
            Command command = Parser.getCommand(input);
            String output = command.execute(this.tasks, this.storage);
            return output;
        } catch (DukeException e) {
            return Ui.getErrorMessageString(e);
        }
    }

}