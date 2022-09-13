package duke;

import java.io.IOException;
import java.io.File;
import java.util.Scanner;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Duke {

    private Storage storage;
    private TaskList taskList;
    private Ui ui;
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

        /**
         *
         * @param
         * @throws IOException
         */
    public Duke() throws IOException {
        ui = new Ui();
        storage = new Storage(new File("src/main/java/duke.txt"));
        taskList = storage.loadFile();
    }

    /**
     * runs the main logic and program of the bot
     */
    public String getResponse(String input) {

        Scanner sc = new Scanner(System.in);

        if (input.equals("bye")) {
            Platform.exit();
            return ui.bye();
        }

        try {
            return Parser.parse(input, taskList, ui, storage);
        } catch (DukeException e){
            return ("Something went wrong " + e.getMessage());
        }
    }
}
