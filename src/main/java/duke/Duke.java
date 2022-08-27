package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;

/**
 * Contains the logic for Duke
 */
public class Duke {
    public static final String DEFAULT_FILE_NAME = "todolist.txt";
    private final Ui ui;
    private final Storage storage;

    /**
     * Constructs an instance of Duke, creates Ui and Storage instance.
     * @return Duke object instance.
     * @see Ui
     * @see Storage
     */
    public Duke() {
        this.ui = new Ui(System.in, System.out);
        this.storage = Storage.of(DEFAULT_FILE_NAME);
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) throws DukeException {
        boolean isExit = false;

        while (!isExit) {
            try {
                Command c = Parser.parse(input);
                String output = c.execute(this.storage.loadSavedData(), this.ui, this.storage);
                isExit = c.isExit(c);
                return output;
            } catch (Exception e) {
                return e.getMessage();
            }
        }
        return "";
    }
}
