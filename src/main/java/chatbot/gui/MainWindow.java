package chatbot.gui;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import chatbot.Zlimez;
import chatbot.ui.Response;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {
    private static long shutDownBufferTime = 2;

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;

    private Zlimez zlimez;
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private Image zlimezImage = new Image(this.getClass().getResourceAsStream("/images/Reg.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setZlimez(Zlimez d) {
        zlimez = d;
        String greet = zlimez.start();
        dialogContainer.getChildren().addAll(
                DialogBox.getZlimezDialog(greet, zlimezImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        if (zlimez.checkStatus()) {
            String input = userInput.getText();
            String response = zlimez.getResponse(input);
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getZlimezDialog(response, zlimezImage)
            );
            userInput.clear();
        } else {
            dialogContainer.getChildren().addAll(
                    DialogBox.getZlimezDialog(Response.SLEEPING, zlimezImage)
            );
        }

        // Check if application should exit
        if (!zlimez.checkStatus()) {
            CompletableFuture.delayedExecutor(shutDownBufferTime, TimeUnit.SECONDS).execute(() -> {
                Platform.exit();
            });
        }
    }
}
