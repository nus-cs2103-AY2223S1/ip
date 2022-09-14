package duke;
import java.io.IOException;
import java.util.Objects;

import duke.command.Command;

/**
 * Main class of Duke.
 *
 * @author Lim Ai Lin
 */
public class Duke {

    private final Storage STORAGE;
    private TaskList tasks;
    private final Ui UI;
    private final Parser PARSER;

    /**
     * Creates a Duke object which saves all tasks into the filePath.
     *
     * @param filePath The text file to save the tasks.
     */
    public Duke(String filePath) {
        UI = new Ui();
        PARSER = new Parser();
        STORAGE = new Storage(filePath);
        try {
            tasks = new TaskList(STORAGE.load());
        } catch (IOException e) {
            UI.showLoadingError();
        }
    }

    protected String run(String input) throws DukeException {
        if (!Objects.equals(input, "bye")) {
            Command c = PARSER.parse(input);
            return c.execute(tasks, UI, STORAGE);
        } else {
            return UI.exit();
        }

    }

    public String getResponse(String input) throws DukeException {
        return run(input);
    }
}
