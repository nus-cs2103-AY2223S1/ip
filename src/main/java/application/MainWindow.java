package application;

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
import tobtob.TobTob;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {
    private static final int DELAY_TIME = 500;

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private TobTob tobTob;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image tobTobImage = new Image(this.getClass().getResourceAsStream("/images/tobtob.png"));

    /**
     * Initializes {@link MainWindow}
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().add(DialogBox.getTobTobDialog(TobTob.getGreeting(), tobTobImage));
    }

    /**
     * Sets {@link TobTob}
     *
     * @param tobTob {@TobTob}
     */
    public void setTobTob(TobTob tobTob) {
        this.tobTob = tobTob;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText().strip();
        String response = tobTob.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getTobTobDialog(response, tobTobImage)
        );

        if (!response.startsWith("Oopsieee!")) {
            userInput.clear();
        }

        //@@author cliftonfelix-reused
        //Reused from https://github.com/vishandi/ip/
        //with minor modification
        if (tobTob.isByeCommand(input)) {
            TimerTask closingPlatform = new TimerTask() {
                @Override
                public void run() {
                    Platform.exit();
                    System.exit(0);
                }
            };
            Timer timer = new Timer();
            timer.schedule(closingPlatform, DELAY_TIME);
        }
    }
}
