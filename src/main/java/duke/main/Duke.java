package duke.main;

import java.time.format.DateTimeParseException;

import duke.command.Command;
import duke.exception.DukeException;
import duke.exception.MissingArgumentException;
import duke.exception.MissingDescriptionException;
import duke.exception.MissingIndexException;
import duke.exception.MissingTimeException;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;


/**
 * Main class for Duke. Contains objects handling storage, the task list and Ui.
 */
public class Duke {

    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/duke.png"));

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
        this.taskList = new TaskList(this.storage.load());
    }

    /**
     * Handles input for Duke.
     * @param input Input to be handled.
     */
    public void handleInput(String input) {
        try {
            Command c = Parser.parse(input);
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
    }

    /**
     * Gets the Ui object of duke.
     * @return Ui object of duke.
     */
    public Ui getUi() {
        return this.ui;
    }
}
