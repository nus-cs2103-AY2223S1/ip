import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import ava.Ava;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/** The main window controller. */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;

    @FXML
    private VBox dialogContainer;

    @FXML
    private TextField userInput;

    /** The Ava object. */
    private Ava ava;

    /** The user's avatar. */
    private final Image userImage = new Image(
            Objects.requireNonNull(this.getClass().getResourceAsStream("/images/user.png")));

    /** The bot's avatar. */
    private final Image avaImage = new Image(
            Objects.requireNonNull(this.getClass().getResourceAsStream("/images/ava.png")));

    /** Initialises the window. */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().addAll(
                DialogBox.getAvaDialog("*booting up......*" + "\n" + "I'm the AvaBot v1.0!", avaImage));
    }

    /** Mounts Ava. */
    public void setAva(Ava ava) {
        this.ava = ava;
    }

    /** Handles the user's input. */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = ava.run(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getAvaDialog(response, avaImage));
        userInput.clear();

        if (ava.isBye()) {
            CompletableFuture.delayedExecutor(2, TimeUnit.SECONDS).execute(Platform::exit);
        }
    }
}
