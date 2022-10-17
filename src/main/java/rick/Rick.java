package rick;

import java.io.IOException;

import rick.commands.Command;
import rick.tasks.TaskList;

/**
 * Main class for Rick program. This takes care of Rick's main logic, including
 * startup, teardown and reading and writing to the file.
 */
public class Rick {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    private static final String DEFAULT_FILE_PATH = "data/rick.txt";

    /**
     * Constructor for Rick class. This takes in the path of the file to be read
     * and written to.
     * 
     * @param filePath The path of the file to be read and written to.
     */
    public Rick(String filePath) {
        try {
            this.ui = new Ui();
            this.storage = new Storage(filePath);
            this.tasks = new TaskList(storage.load());
        } catch (RickException e) {
            ui.print(ui.showError(e));
            this.tasks = new TaskList();
        } catch (IOException e) {
            ui.print(e.getMessage());
        }
        assert (this.tasks != null);
        assert (this.storage != null);
        assert (this.ui != null);
    }

    /**
     * Constructor for Rick if no file path is specified.
     */
    public Rick() {
        this(DEFAULT_FILE_PATH);
    }

    public String getResponse(String userInput) {
        String response = "";
        try {
            Command c = Parser.parseCommand(userInput);
            response += c.execute(tasks, ui, storage);
        } catch (RickException e) {
            response = e.getMessage();
        }
        assert (response != null);
        return response;
    }
}
