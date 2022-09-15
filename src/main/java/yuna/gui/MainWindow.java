package yuna.gui;

import java.util.Timer;
import java.util.TimerTask;

import yuna.Yuna;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

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

    private Yuna yuna;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/Zoro.jpeg"));
    private Image yunaImage = new Image(this.getClass().getResourceAsStream("/images/Yuna.jpeg"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setYuna(Yuna y) {
        yuna = y;
    }

    public void sendGreeting() {
        dialogContainer.getChildren().add(DialogBox.getYunaDialog(yuna.getGreeting(), yunaImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Yuna's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = yuna.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getYunaDialog(response, yunaImage)
        );
        userInput.clear();

        if (response.equals("Bye. Hope to see you again soon!")) {
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    Platform.exit();
                }
            }, 2000);
        }
    }
}
