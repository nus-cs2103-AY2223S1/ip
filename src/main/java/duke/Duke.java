package duke;

import java.nio.file.Path;
import java.nio.file.Paths;

import duke.response.DukeResponse;
import duke.response.ExceptionResponse;
import duke.response.ReadFileResponse;
import duke.response.WriteFileResponse;

/**
 * Personal assistant chatbot which can read and save tasks.
 */
public class Duke {
    private final Storage storage;
    private DukeList list;
    private final DukeUi ui;

    /**
     * Constructor for Duke.
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
        DukeResponse.intro();
    }

    /**
     * Main method.
     * @param args Arguments passed to program.
     */
    public static void main(String[] args) {
        Path dataPath = Paths.get("data", "duke.txt");

        new Duke(dataPath).run();

        DukeResponse.outro();
    }

    /**
     * Runs Duke.
     */
    private void run() {
        boolean isRunning = true;

        while (isRunning) {
            DukeResponse response = this.ui.readInput(this.list);
            try {
                if (response.isExit()) {
                    isRunning = false;
                    this.ui.closeScanner();
                }
                response.run();
            } catch (DukeException e) {
                this.ui.showError(e);
            }
        }

        try {
            new WriteFileResponse(this.list, this.storage).run();
        } catch (DukeException e) {
            new ExceptionResponse(e).run();
        }
    }
}
