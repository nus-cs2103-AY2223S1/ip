package duke;

import java.nio.file.Path;
import java.nio.file.Paths;

import duke.response.DukeResponse;
import duke.response.ExceptionResponse;
import duke.response.ReadFileResponse;

/**
 * Personal assistant chatbot which can read and save tasks.
 */
public class Duke {
    private final Storage storage;
    private DukeList list;
    private final DukeUi ui;

    /**
     * Constructor for Duke.
     */
    public Duke() {
        this(Paths.get("data", "duke.txt"));
    }

    /**
     * Constructor for Duke.
     *
     * @param dataPath Path to task list data file.
     */
    public Duke(Path dataPath) {
        this.storage = new Storage(dataPath);
        this.ui = new DukeUi();
        try {
            new ReadFileResponse().run();
            this.list = new DukeList(this.storage.read());
        } catch (DukeException e) {
            new ExceptionResponse(e).run();
            this.list = new DukeList();
        }
    }

    /**
     * Gets a response from Duke.
     *
     * @param input The user input.
     * @return A String of the response from Duke.
     */
    public String getResponse(String input) {
        DukeResponse response = this.ui.readInput(input, this.list);
        try {
            if (response.isExit()) {
                this.saveData();
            }

            return response.run();
        } catch (DukeException e) {
            return new ExceptionResponse(e).run();
        }
    }

    /**
     * Saves data to storage.
     */
    public void saveData() {
        try {
            this.storage.write(this.list);
        } catch (DukeException e) {
            System.err.println(e.getMessage());
        }
    }
}
