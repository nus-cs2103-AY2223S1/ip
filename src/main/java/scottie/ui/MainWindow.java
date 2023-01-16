package scottie.ui;

import java.util.Objects;

import javafx.application.Platform;
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
 * Code adapted from this guide: https://se-education.org/guides/tutorials/javaFxPart4.html
 */
public class MainWindow extends AnchorPane implements Ui {
    private static final String USER_IMAGE_PATH = "/images/user.png";
    private static final String SCOTTIE_IMAGE_PATH = "/images/scottie.png";
    private static final String SCOTTIE_ERROR_IMAGE_PATH = "/images/scottie_error.png";

    private static final String WELCOME_MESSAGE = "Oh, hey buddy! I'm Scottie!\n"
            + "What's up?";
    private static final int END_PROGRAM_DELAY = 2000;

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Scottie scottie;
    private boolean isProgramEnded = false;

    private final Image userImage = new Image(
            Objects.requireNonNull(this.getClass().getResourceAsStream(USER_IMAGE_PATH)));
    private final Image scottieImage = new Image(
            Objects.requireNonNull(this.getClass().getResourceAsStream(SCOTTIE_IMAGE_PATH)));
    private final Image scottieErrorImage = new Image(
            Objects.requireNonNull(this.getClass().getResourceAsStream(SCOTTIE_ERROR_IMAGE_PATH)));

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
        if (this.isProgramEnded) {
            // Ignore inputs from user while program is about to be closed.
            return;
        }
        String input = this.userInput.getText();
        this.userInput.clear();
        if (input.isBlank()) {
            return;
        }
        this.dialogContainer.getChildren().add(DialogBox.getUserDialog(input, this.userImage));
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
        int i = 1;
        StringBuilder sb = new StringBuilder();
        for (Object obj : iterable) {
            if (i != 1) {
                sb.append("\n");
            }
            sb.append(String.format("%d. %s", i, obj));
            i++;
        }
        this.showMessage(sb.toString());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void showError(String errorMessage) {
        this.dialogContainer.getChildren().add(DialogBox.getScottieErrorDialog(errorMessage, this.scottieErrorImage));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void showFormattedError(String message, Object... args) {
        this.showError(String.format(message, args));
    }

    /**
     * Closes the GUI after a short delay.
     */
    @Override
    public void endProgram() {
        this.isProgramEnded = true;
        new Thread(() -> {
            try {
                Thread.sleep(END_PROGRAM_DELAY);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Platform.exit();
        }).start();
    }
}
