package duke;

import java.util.Objects;

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

/**
 * The Ui class handles the different interactions with the user.
 */

public class Ui extends Application {
    public static final String GREETING = "Hi friend! How may I help you?";
    public static final String FAREWELL = "See you soon, friend!";
    public static final String CONFUSED = "I can't understand. Please enter a proper command, friend!";
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private final Image userImage = new Image(Objects.requireNonNull(
            this.getClass().getResourceAsStream("/images/chimchar.png")));
    private final Image dukeImage = new Image(Objects.requireNonNull(
            this.getClass().getResourceAsStream("/images/pikachu.png")));

    /**
     * Shows a message to the user.
     *
     * @param message a relevant message to be displayed by the Ui.
     */
    public static void echo(String message) {
        System.out.println(message);
    }

    /**
     * Starts the GUI.
     *
     * @param stage the primary stage for this application, onto which
     *              the application scene can be set.
     *              Applications may create other stages, if needed,
     *              but they will not be primary stages.
     */
    @Override
    public void start(Stage stage) {
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);
        userInput = new TextField();
        sendButton = new Button("Send");
        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);
        scene = new Scene(mainLayout);

        // formatting
        stage.setTitle("Duke");
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
        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        // functionalities
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });

        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
        stage.setScene(scene);
        stage.show();

        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog(new Label(Ui.GREETING), new ImageView(dukeImage))
        );
    }

    /**
     * Handles the user input by feeding it into Duke.
     * Replies with a relevant message.
     */
    private void handleUserInput() {
        // Show input in UI, feed input to duke
        String entry = userInput.getText();
        Label userText = new Label(entry);
        dialogContainer.getChildren().add(
                DialogBox.getUserDialog(userText, new ImageView(userImage))
        );
        userInput.clear();

        // Process input and update data, reply in UI
        Label dukeText = new Label(Duke.process(entry));
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(dukeText, new ImageView(dukeImage))
        );
    }
}
