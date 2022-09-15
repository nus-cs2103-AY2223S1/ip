package duke.main;

import java.time.format.DateTimeParseException;

import duke.command.Command;
import duke.exception.DukeException;
import duke.exception.MissingArgumentException;
import duke.exception.MissingDescriptionException;
import duke.exception.MissingIndexException;
import duke.exception.MissingTimeException;

/**
 * Main class for Duke. Contains objects handling storage, the task list and Ui.
 */
public class Duke {

    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    /**
     * Constructs a new Duke object without any parameters.
     */
    public Duke() {
        this.ui = new Ui();
        try {
            this.storage = new Storage(Storage.FILE_PATH);
        } catch (DukeException e) {
            this.ui.showSavingError();
        }
        this.ui.setMessageStatus(this.storage.getDoesFileExist() ? 1 : 0);
        this.taskList = new TaskList(this.storage.load());
    }

    /**
     * Constructs a new Duke object which reads the save from a file at path.
     * @param path The path to read the save from.
     */
    public Duke(String path) {
        this.ui = new Ui();
        try {
            this.storage = new Storage(path);
        } catch (DukeException e) {
            this.ui.showSavingError();
        }
        this.ui.setMessageStatus(this.storage.getDoesFileExist() ? 1 : 0);
        this.taskList = new TaskList(this.storage.load());
    }

    /**
     * Handles input for Duke.
     * @param input Input to be handled.
     */
    public void handleInput(String input) {
        boolean isExit = false;
        try {
            Command c = Parser.parse(input);
            isExit = c.isExit();
            c.execute(this.taskList, this.ui, this.storage);
        } catch (NumberFormatException e) {
            this.ui.showNotANumber();
        } catch (MissingIndexException e) {
            this.ui.showMissingIndex();
        } catch (MissingDescriptionException e) {
            this.ui.showMissingDescription();
        } catch (MissingTimeException e) {
            this.ui.showMissingTime();
        } catch (DateTimeParseException e) {
            this.ui.showInvalidTime();
        } catch (MissingArgumentException e) {
            this.ui.showMissingArgument();
        }

        try {
            this.storage.save(this.taskList);
        } catch (DukeException e) {
            this.ui.showSavingError();
        }

        if (isExit) {
            this.ui.closeMainWindow();
        }
    }

    /**
     * Gets the Ui object of duke.
     * @return Ui object of duke.
     */
    public Ui getUi() {
        return this.ui;
    }

    /**
     * Exits Duke.
     */
    public void exit() {
        this.ui.showBye();
        this.ui.closeMainWindow();
    }
}
