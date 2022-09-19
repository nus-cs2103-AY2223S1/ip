package duke;

import duke.commands.Command;
import duke.tasks.TaskList;
import java.io.IOException;

/*
 * Main class for Duke program. This takes care of Duke's main logic, including 
 * startup, teardown and reading and writing to the file.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    private static final String DEFAULT_FILE_PATH = "data/duke.txt";

    /*
     * Constructor for Duke class. This takes in the path of the file to be read
     * and written to.
     * 
     * @param filePath The path of the file to be read and written to.
     */
    public Duke(String filePath) {
        try {
            this.ui = new Ui();
            this.storage = new Storage(filePath);
            this.tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.print(ui.showError(e));
            this.tasks = new TaskList();
        } catch (IOException e) {
            ui.print(e.getMessage());
        }
        assert (this.tasks != null);
        assert (this.storage != null);
        assert (this.ui != null);
    }

    /*
     * Constructor for Duke if no file path is specified.
     */
    public Duke() {
        this(DEFAULT_FILE_PATH);
    }

    public String getResponse(String userInput) {
        String response = "";
        try {
            Command c = Parser.parseCommand(userInput);
            response += c.execute(tasks, ui, storage);
        } catch (DukeException e) {
            response = e.getMessage();
        }
        assert (response != null);
        return response;
    }
}
