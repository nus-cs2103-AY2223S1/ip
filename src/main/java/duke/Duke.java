package duke;

import duke.command.Command;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import javafx.stage.Stage;

/**
 * The Duke class represent a chatbot called Duke that is able to take in user inputs
 * and save these input in a storage.
 */
public class Duke {
    private final Storage storage;
    private TaskList taskList;
    private final Parser parser;
    private final Stage stage;

    /**
     * Initializes a Duke chatbot.
     */
    public Duke(Stage stage) throws DukeException {
        this.storage = new Storage("data/duke.txt");
        this.parser = new Parser();
        this.taskList = storage.load();
        this.stage = stage;
    }

    public String getResponse(String input) {
        try {
            Command currentCommand = this.parser.parse(input);
            if (currentCommand.getIsTerminator()) {
                end();
            }
            return currentCommand.execute(taskList, storage);
        } catch (DukeException e) {
            return "OOPS! " + e.getMessage();
        }
    }

    private void end() {
        stage.close();
        System.exit(0);
    }
}
