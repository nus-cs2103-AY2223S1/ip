package ui;

import java.util.Timer;
import java.util.TimerTask;

import bocil.Bocil;
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
    private static final int DELAY = 700;
    private static final String END_COMMAND = "bye";
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;
    private Bocil bocil;
    private final Image bocilImage = new Image(this.getClass().getResourceAsStream("/images/bocil.png"));
    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/bob.png"));

    /**
     * Initializes the Main Window with the Bocil's introduction.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().add(DialogBox.getBocilDialog(Bocil.introduce(), bocilImage));
    }

    /**
     * Sets a {@link Bocil} object.
     *
     * @param bocil {@link Bocil} object to be set.
     */
    public void setBocil(Bocil bocil) {
        this.bocil = bocil;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Bocil's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = bocil.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getBocilDialog(response, bocilImage)
        );
        userInput.clear();

        if (input.equals(END_COMMAND)) {
            bocil.endProgram();
            //@@author JasonCP14-reused
            //Reused from https://github.com/godjuansan/ip/ with minor modification
            TimerTask closingPlatform = new TimerTask() {
                @Override
                public void run() {
                    Platform.exit();
                    System.exit(0);
                }
            };
            Timer timer = new Timer();
            timer.schedule(closingPlatform, DELAY);
        }
    }
}
