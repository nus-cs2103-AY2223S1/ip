package duke;

import java.util.Scanner;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.Region;


/**
 * Main class for Duke
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));


    /**
     * Constructor for Duke class
     *
     * Creates Ui and Storage instance
     */

    public Duke() {
        ui = new Ui();
        storage = new Storage("data/tasks.txt");
        tasks = new TaskList(storage.load());

    }


    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        if (input.equals("bye")) {
            return ui.sayBye();
        }
        else {
            try {
                return (Parser.parse(input, tasks, ui, storage));

            } catch (DukeException e) {
                return ("Something went wrong " + e.getMessage());
            }
        }
    }

}
