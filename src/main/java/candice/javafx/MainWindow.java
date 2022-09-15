package candice.javafx;

import candice.Candice;

import candice.Ui;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {
    /** The pane that contains all the other nodes */
    @FXML
    private ScrollPane scrollPane;
    /** The container that displays dialog */
    @FXML
    private VBox dialogContainer;
    /** Text field that allows user to input their text */
    @FXML
    private TextField userInput;
    /** Button for users to send their text */
    @FXML
    private Button sendButton;
    /** Instance of Candice that is associated with this GUI */
    private Candice candice;
    /** Image of the users in the dialog boxes */
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.jpg"));
    /** Image of Candice in the dialog boxes */
    private Image candiceImage = new Image(this.getClass().getResourceAsStream("/images/DaCandice.jpg"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Assigns an instance of Candice to this GUI.
     *
     * @param candice The instance of Candice to associate with this GUI.
     */
    public void setCandice(Candice candice) {
        this.candice = candice;
    }

    /**
     * Adds the start up message to the dialog container when Candice starts.
     */
    public void sendStartMessage() {
        String startMessage = Ui.getMessageForStartingUp();
        dialogContainer.getChildren().add(DialogBox.getCandiceDialog(startMessage, candiceImage));
    }
    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     * If the input is "bye", displays the exit message for 1 second before closing the program.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = candice.run(input);

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getCandiceDialog(response, candiceImage)
        );

        // closes the program if bye is inputted
        if (input.equals("bye")) {
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    System.exit(0);
                }
            }, 1000);
        }

        userInput.clear();
    }
}
