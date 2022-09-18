package duke.ui;

import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import duke.Duke;
import duke.managers.ParserManager;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

/**
 * @author Emily Ong Hui Qi
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private ImageView dukePicture;
    @FXML
    private Button sendButton;
    @FXML
    private Label appLabel;
    @FXML
    private Accordion commandsAccordion;

    private Duke duke;

    private final Image userImage = new Image(
            Objects.requireNonNull(this.getClass().getResourceAsStream("/images/User.png"))
    );
    private final Image dukeImage = new Image(Objects.requireNonNull(
            this.getClass().getResourceAsStream("/images/Duke.png"))
    );

    private final String fontType = "Monaco";
    private final int fontSize = 12;
    private final int defaultPadding = 8;
    private final int dialogBoxMinHeight = 100;
    private final double opacityThreshold = 0.7;

    protected void setDuke(Duke duke) {
        this.duke = duke;
    }

    private void disableSendButtonIfEmptyUserInput() {
        this.sendButton.setDisable(this.userInput.getText().length() == 0);
    }

    /**
     * Initializes the main window application.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        this.dukePicture.setImage(this.dukeImage);
        this.appLabel.setText(Duke.NAME);
        this.disableSendButtonIfEmptyUserInput();

        Map<String, String> commandsMap = ParserManager.getAllCommands();
        this.commandsAccordion.getPanes().addAll(
            commandsMap.keySet().stream().map(commandWord -> {
                Label label = new Label(commandsMap.get(commandWord));
                label.setWrapText(true);
                label.setFont(new Font(this.fontType, this.fontSize));
                label.setTextAlignment(TextAlignment.LEFT);
                label.setMinHeight(this.dialogBoxMinHeight);
                label.setPadding(new Insets(this.defaultPadding));
                return new TitledPane(commandWord, label);
            }).collect(Collectors.toList())
        );

        // Greet the user
        this.dialogContainer.getChildren().add(DialogBox.getDukeDialog(Duke.MESSAGE_GREETING, this.dukeImage));
    }

    @FXML
    private void toggleCommandsAccordion() {
        this.commandsAccordion.setVisible(!this.commandsAccordion.isVisible());
        if (this.commandsAccordion.isVisible()) {
            this.dialogContainer.setOpacity(this.opacityThreshold);
        } else {
            this.dialogContainer.setOpacity(1);
        }
    }

    @FXML
    private void closeCommandsAccordion() {
        if (!this.commandsAccordion.isVisible()) {
            return;
        }
        this.toggleCommandsAccordion();
    }

    @FXML
    private void handleUserInput() {
        this.disableSendButtonIfEmptyUserInput();
    }

    @FXML
    private void handleCommand() {
        String input = this.userInput.getText();
        if (input.length() == 0) {
            return;
        }
        String response = this.duke.handleCommand(input);

        this.dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, this.userImage),
                DialogBox.getDukeDialog(response, this.dukeImage)
        );
        this.userInput.clear();
        this.userInput.requestFocus();
        this.disableSendButtonIfEmptyUserInput();
    }
}
