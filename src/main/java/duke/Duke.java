package duke;

import java.util.Objects;
import java.util.Scanner;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


/**
 * Duke is a personal chatbot to keep track of things.
 *
 * @author Aaron Tan
 */
public class Duke {

    private static final Scanner SCANNER = new Scanner(System.in);
    private TaskList tasks;
    private Ui ui;
    private Storage storage;
    private Parser parser;
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/User.jpg"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/Duke.jpg"));

    /**
     * Constructs an instance of Duke.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage();
        parser = new Parser();
        run();
    }


    private void run() {
        ui.introduction();
        tasks = storage.readData();
    }
    public static void main(String[] args) {
        new Duke().run();
    }


    /**
     * @param input User input to parse
     * @return Returns a String to respond to the user input.
     */
    protected String getResponse(String input) {
        if (input.equals("bye")) {
            storage.saveData(tasks);
        }
        return parser.process(input, tasks);
    }
}
