package duke.main;

import javafx.scene.image.Image;

import duke.command.Command;
import duke.exception.DukeException;

/**
 * Starts the duke chatbot.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/duke.png"));

    private static final String filePath = "data/duke.txt";

    /**
     * Initializes the chatbot.
     */
     public Duke() {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Handles input for Duke.
     * @param input Input to be handled.
     */
    public void handleInput(String input) {
        try {
            Command c = Parser.parse(input);
            c.execute(this.tasks, this.ui, this.storage);
        } catch (DukeException e) {
            this.ui.outputMessage(e.getMessage());
        }

        try {
            this.storage.save(this.tasks);
        } catch (DukeException e) {
            this.ui.showLoadingError();
        }
    }

    public Ui getUi() {
        return this.ui;
    }
}
