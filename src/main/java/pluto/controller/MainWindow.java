package pluto.controller;

import java.util.Timer;
import java.util.TimerTask;

import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import pluto.Pluto;
import pluto.Ui;

/**
 * Layout of application.
 */
public class MainWindow extends AnchorPane {
    /** Scrollpane for the GUI */
    @FXML
    private ScrollPane scrollPane;
    /** Container for all Dialog Boxes */
    @FXML
    private VBox dialogContainer;
    /** Input field for the user */
    @FXML
    private TextField userInput;
    /** Bot generating responses based on user input */
    private Pluto pluto;

    /** Image of the user */
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    /** Image of Pluto */
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    /**
     * Initializes the window.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(Ui.showWelcome(), dukeImage)
        );
    }

    /**
     * Sets the chatbot for the application.
     * @param bot Pluto chatbot.
     */
    public void setPluto(Pluto bot) {
        pluto = bot;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other
     * containing Duke's reply and then appends them to the
     * dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = pluto.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();
        if (response.equals("See You Later!")) {
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    System.exit(0);
                }
            }, 1000L);
        }
    }
}
