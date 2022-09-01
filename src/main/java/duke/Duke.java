package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskManager;
import duke.ui.Ui;
import java.io.IOException;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;


/**
 * Represents a Duke class
 *
 * @author Khor Jun Wei
 * @version CS2103T AY22/23 Sem 1
 */
public class Duke {

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private final Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private final Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    /**
     * Represents a storage for texts.
     */
    private final Storage storage;

    /**
     * Represents a list of tasks.
     */
    private final TaskManager taskManager;

    /**
     * Represents a User interface.
     */
    private final Ui ui;

    /**
     * Basic constructor required for Launcher to work.
     */
    public Duke() {
        this.ui = new Ui();
        this.storage = new Storage("data/tasks.txt");
        this.taskManager = new TaskManager(storage.load());
    }

    /**
     * Represents a constructor method for Duke class
     * @param filepath path to file
     */
    public Duke(String filepath) {
        this.ui = new Ui();
        this.storage = new Storage(filepath);
        try {
            this.taskManager = new TaskManager(storage.load());
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * Runs the duke program.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(taskManager, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.toString());
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * Main method.
     *
     * @param args given arguments
     */
    public static void main(String[] args) {

        new Duke("data/tasks.txt").run();

    }



    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(taskManager, ui, storage);
        } catch (DukeException e) {
            return e.toString();
        } catch (IOException e) {
            return e.toString();
        }
    }
}
