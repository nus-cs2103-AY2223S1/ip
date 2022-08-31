package duke.ui;

import java.util.Objects;

import duke.Duke;
import duke.command.CommandException;
import duke.command.response.CommandResponse;
import duke.data.storage.StorageException;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class MainWindow extends AnchorPane {
    private static final Long EXIT_DELAY = 1000L;
    private final Image dukeDisplayImage = new Image(
        Objects.requireNonNull(this.getClass().getResourceAsStream("/images/duke.png")));
    private final Image userDisplayImage = new Image(
        Objects.requireNonNull(this.getClass().getResourceAsStream("/images/user.png")));

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;

    private Duke duke;

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setDuke(Duke duke) {
        this.duke = duke;
        dialogContainer.getChildren()
            .add(DialogBox.getDukeDialogBox(duke.getWelcomeMessage(), dukeDisplayImage));
        try {
            this.duke.loadCache();
        } catch (StorageException cacheError) {
            dialogContainer.getChildren().add(
                DialogBox.getDukeDialogBox(formatError(cacheError.getMessage()), dukeDisplayImage));
        }
    }

    private String formatError(String errorMessage) {
        return String.format("X %s", errorMessage);
    }

    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String dukeResponseStr = "";
        DialogBox userDialogBox = DialogBox.getUserDialogBox(input, userDisplayImage);
        try {
            CommandResponse commandResponse = duke.getResponse(input);
            dukeResponseStr = commandResponse.getResponseStr();
        } catch (CommandException | StorageException error) {
            dukeResponseStr = formatError(error.getMessage());
        } finally {
            dialogContainer.getChildren().addAll(
                userDialogBox,
                DialogBox.getDukeDialogBox(dukeResponseStr, dukeDisplayImage)
            );
            userInput.clear();
        }

        if (duke.hasTerminated()) {
            new Thread(() -> {
                try {
                    Thread.sleep(EXIT_DELAY);
                } catch (InterruptedException interruptedException) {
                    interruptedException.printStackTrace();
                } finally {
                    Platform.exit();
                }
            }).start();
        }
    }
}
