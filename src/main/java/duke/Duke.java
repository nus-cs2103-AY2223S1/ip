package duke;

import java.io.FileNotFoundException;

import duke.command.Command;
import javafx.stage.Stage;

/**
 * Duke is a Personal Assistant Chatbot that helps a person to keep track of various things.
 *
 * @author Samsation
 * @version CS2103T AY 22/23 Sem 1
 *
 */

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private boolean isTerminated = false;

    /**
     * A constructor for Duke.
     *
     * @param stage The current Stage of the Application.
     */
    public Duke(Stage stage) {
        this.ui = new Ui(stage);
        this.storage = new Storage(System.getProperty("user.home") + "/data/duke.txt");
        try {
            this.tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            this.tasks = new TaskList();
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * A method that generates a response from Duke.
     *
     * @param fullCommand The input command by the user.
     */
    public String getResponse(String fullCommand) {
        try {
            Command c = Parser.parse(fullCommand);
            return c.execute(tasks, ui, storage);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}
