package duke;

import duke.command.Command;
import duke.exception.DukeException;

import java.io.FileNotFoundException;

/**
 * The Duke class encapsulates a Personal Assistant Chatbot named Duke to help keep track of various tasks.
 *
 * @author Jerome Hoo Jun Jie
 */
public class Duke {
    /** The storage to load tasks from the file and saves tasks in the file specified */
    private Storage storage;
    /** A collection to store tasks into a list */
    private TaskList tasks;
    /** A user interface to interact with the user */
    private Ui ui;

    /**
     * Instantiates the Duke object.
     *
     * @param filePath the relative path provided for Duke to load and save files.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /** Saves the file when Duke is closed. */
    public void save() {
        this.storage.save(tasks);
    }

    /** Loads the file when Duke is opened. */
    public String load() {
        try {
            return this.storage.printFileContents(storage.getFilePath());
        } catch (FileNotFoundException e) {
            return e.getMessage();
        }
    }

    /**
     * Returns the greeting message when Duke is opened
     *
     * @return Greeting message.
     */
    public String greeting() {
        return ui.printGreeting();
    }

    /**
     * Returns the response from Duke after the user input is read.
     *
     * @return Response from Duke.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            c.execute(tasks, ui);
            storage.save(tasks);
            return c.response();
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}
