package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.model.TaskList;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.ui.Ui;
import java.util.Scanner;

/**
 * The main class for the chatbot.
 */
public class Duke {

    private Storage storage;
    private TaskList tasklist;
    private Ui ui;

    /**
     * A constructor for the chatbot.
     *
     * @param filePath the file path for storage
     */
    public Duke (String filePath) {
        Scanner sc = new Scanner(System.in);
        this.ui = new Ui(sc);
        this.storage = new Storage(filePath);
        this.tasklist = new TaskList(storage.loadFromFile());
    }

    /**
     * Returns a string representation of a response based on the user's input
     * @param input
     * @return a string representation of the response
     */
    public String getResponse(String input) {
        try {
            Command command = Parser.parse(input);
            return command.execute(tasklist, storage);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}
