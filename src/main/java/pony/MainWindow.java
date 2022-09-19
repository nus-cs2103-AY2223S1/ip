package pony;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    /** Instance of doemon being used */
    private Pony pony;

    /** Image of the user avatar */
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/girl.png"));
    /** Image of the doemon chat bot avatar */
    private Image ponyImage = new Image(this.getClass().getResourceAsStream("/images/pony.png"));

    /**
     * Initializes the main window.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().addAll(
                DialogBox.getPonyDialog(Ui.printWelcome(), ponyImage));
    }

    /**
     * Sets the doemon field.
     *
     * @param p Doemon instance.
     */
    public void setPony(Pony p) {
        pony = p;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the
     * other containing Doemon's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String output = pony.runCommand(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getPonyDialog(output, ponyImage)
        );
        userInput.clear();
        //@@author ciaoosuuu-reused
        //Reused from https://github.com/KSHan29/ip/blob/master/src/main/java/duke/javafx/MainWindow.java
        //with minor modifications
        if (input.split(" ")[0].equals("bye")) {
            PauseTransition delay = new PauseTransition(Duration.seconds(2));
            delay.setOnFinished((event) -> Platform.exit());
            delay.play();
        }
        //@@author
    }
}