package duke.gui;

import duke.chatbot.ChatBot;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;

/**
 * InputContainer is a container that contains the input text field and the "send" button.
 */
public class InputContainer extends HBox {
    private static final double PADDING_VALUE = 10.0;
    private static final double SPACING_VALUE = 10.0;
    private static final double CONTAINER_MIN_WIDTH = 400.0;
    private static final double INPUT_FIELD_MIN_WIDTH = 320.0;
    private static final double BUTTON_MIN_WIDTH = 50.0;

    private TextField userInputField;
    private Button sendButton;

    /**
     * Creates a container to contain the input text field and the "send" button.
     */
    public InputContainer() {
        this.userInputField = new TextField();
        this.sendButton = new Button("Send");

        this.setMinWidth(CONTAINER_MIN_WIDTH);
        this.userInputField.setMinWidth(INPUT_FIELD_MIN_WIDTH);
        this.sendButton.setMinWidth(BUTTON_MIN_WIDTH);

        this.setPadding(new Insets(PADDING_VALUE));
        this.setSpacing(SPACING_VALUE);
        this.setAlignment(Pos.CENTER_LEFT);
        this.getChildren().addAll(this.userInputField, this.sendButton);
    }

    /**
     * Initializes the event handlers of the user input field and the "send" button.
     * Signals the main window to handle user input when an event is triggered.
     *
     * @param mainWindow the main window to signal when an event is triggered
     * @param chatBot a reference to the chatbot to access logs
     */
    public void initializeEventHandlers(MainWindow mainWindow, ChatBot chatBot) {
        this.userInputField.setOnKeyPressed((event) -> {
            if (event.getCode().equals(KeyCode.ENTER)) {
                mainWindow.handleUserInput(this.userInputField.getText());
                this.userInputField.clear();
            }

            if (chatBot.isRunning()) {
                if (event.getCode().equals(KeyCode.UP)) {
                    this.userInputField.clear();
                    this.userInputField.setText(chatBot.getPreviousLog());
                    this.userInputField.end();
                }
                if (event.getCode().equals(KeyCode.DOWN)) {
                    this.userInputField.clear();
                    this.userInputField.setText(chatBot.getNextLog());
                    this.userInputField.end();
                }
            }
        });
        this.sendButton.setOnMouseClicked((event) -> {
            mainWindow.handleUserInput(this.userInputField.getText());
            this.userInputField.clear();
        });
    }
}
