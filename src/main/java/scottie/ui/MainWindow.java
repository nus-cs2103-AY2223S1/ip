package scottie.ui;

import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import scottie.Scottie;

/**
 * Controller for MainWindow. Provides the Layout for the other controls.
 */
public class MainWindow extends AnchorPane implements Ui {
    private static final String WELCOME_MESSAGE = "Hello there! I'm Scottie!\n"
            + "How can I help you?";

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Scottie scottie;

    private final Image userImage = new Image(
            Objects.requireNonNull(this.getClass().getResourceAsStream("/images/user.png")));
    private final Image scottieImage = new Image(
            Objects.requireNonNull(this.getClass().getResourceAsStream("/images/scottie.png")));

    @FXML
    public void initialize() {
        this.scrollPane.vvalueProperty().bind(this.dialogContainer.heightProperty());
    }

    public void setScottie(Scottie scottie) {
        this.scottie = scottie;
    }

    /**
     * Creates a dialog box echoing the user's input and then appends it
     * to the dialog container. Clears the user input after processing.
     * Also sends the user's input to Scottie for Scottie to generate its
     * response.
     */
    @FXML
    private void handleUserInput() {
        String input = this.userInput.getText();
        this.dialogContainer.getChildren().add(DialogBox.getUserDialog(input, this.userImage));
        this.userInput.clear();
        this.scottie.sendInput(input, this);
    }

    /**
     * Creates a dialog box containing Scottie's message to the user
     * and appends it to the dialog container.
     *
     * @param message The message to show to the user.
     */
    private void showMessage(String message) {
        this.dialogContainer.getChildren().add(DialogBox.getScottieDialog(message, this.scottieImage));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void showMessages(String... messages) {
        this.showMessage(String.join("\n", messages));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void showFormattedMessage(String message, Object... args) {
        this.showMessage(String.format(message, args));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void showStartupMessage() {
        this.showMessage(WELCOME_MESSAGE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void showOrderedList(Iterable<?> iterable) {
        this.showMessage(StreamSupport.stream(iterable.spliterator(), false)
                .map(Object::toString)
                .collect(Collectors.joining("\n")));
    }
}
