package dukepro;

import dukepro.exceptions.DukeException;
import dukepro.handlers.Interact;
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
 * Class for DukePro.
 */
public class DukePro extends Application {
    private Interact interact;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/buttercup.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/bubbles.png"));

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    /**
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
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     *
     * @return void.
     */
    private void handleUserInput() {
        Label userText = new Label(userInput.getText());
        Label dukeText;
        try {
            dukeText = new Label(getResponse(interact.handle(userInput.getText())));
        } catch (DukeException e) {
            dukeText = new Label(getResponse(e.toString()));
        }
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(user)),
                DialogBox.getDukeDialog(dukeText, new ImageView(duke))
        );
        userInput.clear();
    }

    /**
     * Sets up the fields of this class.
     *
     * @return void.
     */
    private void setup() {
        interact = new Interact();
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        scrollPane.setPrefSize(385, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        userInput.setPrefWidth(325.0);
        sendButton.setPrefWidth(55.0);
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
    }

    /**
     * Gets a response from DukePro.
     *
     * @param input the input String.
     * @return A String.
     */
    private String getResponse(String input) {
        return "Duke heard: " + input;
    }

    /**
     * Sets Anchor Pane of the GUI.
     *
     * @return void.
     */
    private void setAnchorPane() {
        AnchorPane.setTopAnchor(scrollPane, 1.0);
        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);
        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);
    }

    /**
     * Sets the Stage for the GUI.
     *
     * @return void.
     */
    private void setStage(Stage stage, double height, double width) {
        stage.setScene(scene);
        stage.show();
        stage.setTitle("dukepro");
        stage.setResizable(false);
        stage.setMinHeight(height);
        stage.setMinWidth(width);
    }

    @Override
    public void start(Stage stage) {
        setup();
        String startTxt = interact.start();
        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);
        scene = new Scene(mainLayout);
        setStage(stage, 600.0, 400.0);
        mainLayout.setPrefSize(400.0, 600.0);
        setAnchorPane();

        Label dukeText = new Label(getResponse(startTxt));
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(dukeText, new ImageView(duke))
        );
        sendButton.setOnMouseClicked((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });
        userInput.setOnAction((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });
        userInput.setOnAction((event) -> {
            handleUserInput();
        });
    }

}
