package duke.main;

import duke.DialogueBox;
import duke.parser.Parser;
import duke.save.Storage;
import duke.TaskList;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.image.Image;

/**
 * Main class of the program
 * Stores a taskList of tasks
 * Contains references to elements in GUI
 */
public class Duke {

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    //images to represent user/duke
    private Image user = new Image(this.getClass().getResourceAsStream("/images/finalUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/ajitpai.png"));

    private static TaskList tasks;

    public Duke() {
        tasks = Storage.load();
    }

    private void handleUserInput() {
        dialogContainer.getChildren().addAll(
                DialogueBox.getUserDialog(userInput.getText(), user),
                DialogueBox.getDukeDialog(getResponse(userInput.getText()), duke)
        );
        userInput.clear();
    }


    /**
     * returns the appropriate response as a string
     * @param input
     * @return
     */
    public String getResponse(String input) {
        return Parser.parseData(input, tasks);
    }





}

