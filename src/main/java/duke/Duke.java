package duke;

import java.io.IOException;

//import duke.command.Command;
//import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
//import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
//import javafx.scene.image.ImageView;
//import javafx.scene.layout.AnchorPane;
//import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
//import javafx.stage.Stage;


/**
 * A Personal Assistant Chatbot that helps a person to keep track of various things.
 * CS2103T iP
 * AY22/23 Semester 1
 *
 * @author Perry Wong
 */
public class Duke {

    //GUI
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/duke.png"));

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Instantiates a Duke program.
     *
     * @param filePath Relative path that the data file is stored in.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException | IOException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Returns Duke's response after receiving user's input
     *
     * @param input User's command input
     * @return Response by Duke depending on the command input
     */
    public String getResponse(String input) {
        try {
            return Parser.parse(input).execute(tasks, ui, storage);
        } catch (DukeException | IOException e) {
            return e.getMessage();
        }
    }
}
