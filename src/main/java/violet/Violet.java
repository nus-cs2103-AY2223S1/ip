package violet;

import violet.command.Command;
import violet.exception.VioletException;

import java.io.FileNotFoundException;

/**
 * The Violet class encapsulates a Personal Assistant Chat bot named Violet to help keep track of various tasks.
 *
 * @author Jerome Hoo Jun Jie
 */
public class Violet {
    /** The storage to load tasks from the file and saves tasks in the file specified */
    private Storage storage;
    /** A collection to store tasks into a list */
    private TaskList tasks;
    /** A user interface to interact with the user */
    private Ui ui;

    /**
     * Instantiates the Violet object.
     *
     * @param filePath the relative path provided for Violet to load and save files.
     */
    public Violet(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (VioletException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /** Saves the file when Violet is closed. */
    public void save() {
        this.storage.save(tasks);
    }

    /** Loads the file when Violet is opened. */
    public String load() {
        try {
            return this.storage.printFileContents(storage.getFilePath());
        } catch (FileNotFoundException e) {
            return e.getMessage();
        }
    }

    /**
     * Returns the greeting message when Violet is opened
     *
     * @return Greeting message.
     */
    public String greeting() {
        return ui.printGreeting();
    }

    /**
     * Returns the response from Duke after the user input is read.
     *
     * @return Response from Violet.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            c.execute(tasks, ui);
            storage.save(tasks);
            return c.response();
        } catch (VioletException e) {
            return e.getMessage();
        }
    }
}
