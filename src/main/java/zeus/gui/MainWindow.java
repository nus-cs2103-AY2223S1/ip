package zeus.gui;

import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import zeus.Zeus;

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

    private Zeus zeus;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/sheldoncooper.jpeg"));
    private Image zeusImage = new Image(this.getClass().getResourceAsStream("/images/chatbot.jpeg"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setZeus(Zeus z) {
        zeus = z;
    }

    public void sendGreetings() {
        dialogContainer.getChildren().add(DialogBox.getZeusDialog(zeus.getGreeting(), zeusImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Zeus's reply and then appends them to
     * the dialog container. Clears the user input after processing. If supposed to exit, delays the exit
     * by 2 seconds to allow exit message to be seen by user.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = zeus.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getZeusDialog(response, zeusImage)
        );
        userInput.clear();
        String zeusResponseToBye = "Zeus says:\n" + "Bye. Hope to see you again soon!";
        if (response.equals(zeusResponseToBye)) {
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    Platform.exit();
                }
            },
                    2000);
        }
    }
}

