package duke;

import java.util.Scanner;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.Region;
import javafx.scene.control.Label;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

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
    String getResponse(String input) {
        return Parser.parseData(input, tasks);
    }
    public Duke() {
        tasks = Storage.load();
    }


    public static void main(String[] args) {
        new Duke().run();
    }

    /**
     * DEPRECATED
     * Displays the welcome message.
     * Initializes the scanner to scan for inputs
     * Lets the parser parse the correct input
     * Terminate the program if the user requests for it
     */
    public static void run() {
        Ui.welcomeMessage();
        Scanner sc = new Scanner(System.in);
        String input;
        while (true) {
            input = sc.nextLine();
            //how does the user end this//
            if (input.equals("bye")) {
                Ui.displayMessage(Ui.ENDING_MESSAGE);
                break;
            }
            //if not, parser can parse data
            Parser.parseData(input, tasks);
        }
    }


}

