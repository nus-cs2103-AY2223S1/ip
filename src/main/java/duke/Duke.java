package duke;

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
 * Main Class of this program
 */
public class Duke {
    private TaskList taskList;
    private Parser parser;

    /**
     * Constructor for Duke. Initializes tasklist and parser.
     */
    public Duke() {
        this.taskList = new TaskList(Storage.load());
        this.parser = new Parser(this.taskList);
    }


    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    String getResponse(String input) {
        return "Duke heard: " + input;
    }

    /**
     * Runs the parser to parse user input
     */
    public void run() throws DukeException {
        Ui.showWelcome();

        while (parser.isScanning()) {
            parser.parse();
        }
    }

    public static void main(String[] args) {
        try {
            new Duke().run();
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }
}
