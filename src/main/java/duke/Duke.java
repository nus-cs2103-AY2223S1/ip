package duke;

import duke.JavaFXcomponent.DialogBox;
import duke.Storage.DukeDecoder;
import duke.Parser.ProcessUserInput;
import Ui.Constants;
import duke.TaskList.*;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Duke extends Application {
    // UI basic component
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    // Avatar for user and Duke
    private Image user = new Image(this.getClass().getResourceAsStream("/image/conan.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/image/soccat.jpg"));

    // List to store text entered by the user and display them back to the user when requested
    private static ArrayList<Task> workList = DukeDecoder.loadDataFromList();
    /**
     * Start the program
     */
    private static void greet() {
        System.out.println("Hello I am\n" + Constants.LOGO);
        System.out.println("May I help you?");
    }

    /**
     * Exit when user type 'bye'
     */
    private static void exit() {
        System.out.println("Great that you joined. See you soon. Bye!");
    }

    public static void main(String[] args){
        // Greeting
        Duke.greet();

        // Process
        ProcessUserInput.process(workList);

        // Bye
        Duke.exit();
    }

    /**
     * Iteration 1:
     * Creates a label with the specified text and adds it to the dialog container.
     * @param text String containing text to add
     * @return a label with the specified text that has word wrap enabled.
     */
    private Label getDialogLabel(String text) {
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }

    /**
     * Iteration 2:
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    private void handleUserInput() {
        Label userText = new Label(userInput.getText());
        Label dukeText = new Label(getResponse(userInput.getText()));
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(user)),
                DialogBox.getDukeDialog(dukeText, new ImageView(duke))
        );
        userInput.clear();
    }

    // Change latter
    private String getResponse(String input) {
        return "Duke heard: " + input;
    }

    @Override
    public void start(Stage stage) {
        // Step 1: Container for the content of the chat to scroll
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        // Step 2: Formatting
        stage.setTitle("CS2103T");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);

        mainLayout.setPrefSize(400.0, 600.0);

        scrollPane.setPrefSize(385, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput, 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        // Step 3: Add functionality to handle user input.
        sendButton.setOnMouseClicked((event -> {
            handleUserInput();
        }));

        // Iteration 2
        userInput.setOnAction((event -> {
            handleUserInput();
        }));

        // Step 3*: Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        // Set up
        Scene scene = new Scene(mainLayout);

        stage.setScene(scene);
        stage.show();
    }
}
