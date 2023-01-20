package uwu.gui;

import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import uwu.uwu.UwuBot;

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

    private UwuBot uwuBot;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/UwUser.png"));
    private Image uwuImage = new Image(this.getClass().getResourceAsStream("/images/Uwu.png"));

    /**
     * Initializes the MainWindow.
     */
    @FXML
    public void initialize() {
        scrollPane.setOnScroll(event -> {
            scrollPane.setVvalue(scrollPane.getVvalue() - event.getDeltaX() / dialogContainer.getHeight());
        });

        dialogContainer.heightProperty().addListener((observable, oldValue, newValue) -> {
            scrollPane.setVvalue(1.0);
        });

        sendButton.disableProperty().bind(Bindings.isEmpty(userInput.textProperty()));

        dialogContainer.getChildren().add(DialogBox.getUwuDialog("hellu!\ni am oo woo <:"
                + "\nhow can i be of service today?\ntype 'help' to view the list of commands~", uwuImage));

        assert this.scrollPane != null : "[scrollPane] FXML was improperly configured.";
        assert this.dialogContainer != null : "[dialogContainer] FXML was improperly configured.";
        assert this.userInput != null : "[userInput] FXML was improperly configured.";
        assert this.sendButton != null : "[sendButton] FXML was improperly configured.";
    }

    public void setUwu(UwuBot uwu) {
        uwuBot = uwu;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = uwuBot.getResponse(input);

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getUwuDialog(response, uwuImage)
        );

        userInput.clear();

        if (uwuBot.isEnd(input)) {
            exitProgram();
        }
    }

    /**
     * Exits the program after a delay.
     */
    private void exitProgram() {
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                Platform.exit();
            }
        };

        Timer timer = new Timer("exitTime");
        timer.schedule(timerTask, 1000);
    }
}
