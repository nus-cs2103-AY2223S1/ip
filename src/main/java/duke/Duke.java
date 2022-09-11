package duke;

import duke.command.Command;
import duke.parser.Parser;
import duke.storage.Storage;
import javafx.application.Platform;

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
     * Retrieves a response in accordance to input given.
     * @return String response
     */
    public String getResponse(String input) {
        boolean isExit = false;

        while (!isExit) {
            try {
                Command c = Parser.parse(input);
                String output = c.execute(this.storage.loadSavedData(), this.ui, this.storage);
                if (c.isExit(c)) {
                    Platform.exit();
                    break;
                }
                return output;
            } catch (Exception e) {
                return e.getMessage();
            }
        }
        return "";
    }
}
