package duke;

import task.DukeTask;

import java.util.ArrayList;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;

/**
 * Main class for Duke program.
 * Keeps track of tasks for user.
 *
 * @author Gabriel Yang
 */
public class Duke {
    private Storage storage;
    private ArrayList<DukeTask> tasklist;
    private Ui ui;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/User.jpg"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/Duke.jpg"));
    public static boolean isterminated = false;

    /**
     * Creates the Duke class to initialise program.
     * Initialises the TaskList, Ui, and Storage.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage();
        tasklist = new ArrayList<DukeTask>();
        Storage.setOnce(tasklist, "src/data/list.txt");
        storage.read();
    }

    protected String getResponse(String input) {
        while(!isterminated){
            try {
                return Parser.parse(input).deconstruct(tasklist, ui, storage);
            } catch (Exception e) {
                return "Sorry something went wrong: " + e;
            }
        }
        System.exit(0);
        return null;
    }
}
