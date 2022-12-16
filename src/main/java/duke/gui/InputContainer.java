package duke.gui;

import duke.chatbot.ChatBot;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 * InputContainer is a container that contains the input text field and the "send" button.
 */
public class InputContainer extends HBox {
    private static final Color CONTAINER_COLOUR = Color.web("3f51b5");
    private static final Color INPUT_FIELD_COLOUR = Color.web("e3f2fd");
    private static final Color BUTTON_COLOUR = Color.web("e3f2fd");
    private static final CornerRadii CONTAINER_CORNER_RADII = new CornerRadii(0);
    private static final CornerRadii INPUT_FIELD_CORNER_RADII = new CornerRadii(5.0);
    private static final CornerRadii BUTTON_CORNER_RADII = new CornerRadii(5.0);
    private static final BorderWidths INPUT_FIELD_BORDER_WIDTH = new BorderWidths(2.0);
    private static final BorderWidths BUTTON_BORDER_WIDTH = new BorderWidths(2.0);
    private static final double CONTAINER_INSET_VALUE = 5.0;
    private static final double CONTAINER_SPACING_VALUE = 5.0;
    private static final double BUTTON_MIN_WIDTH = 50.0;
    private static final double BUTTON_FONT_SIZE = 11.0;
    private static final String BUTTON_FONT = "Courier New Bold";

    private final TextField inputField;
    private final Button sendButton;

    /**
     * Creates a container to contain the input text field and the "send" button.
     */
    public InputContainer(MainWindow mainWindow) {
        this.inputField = new TextField();
        this.sendButton = new Button("Send");

        BackgroundFill inputFieldFill = new BackgroundFill(INPUT_FIELD_COLOUR, CONTAINER_CORNER_RADII, Insets.EMPTY);
        BackgroundFill buttonFill = new BackgroundFill(BUTTON_COLOUR, CONTAINER_CORNER_RADII, Insets.EMPTY);
        BorderStroke inputFieldBorder = new BorderStroke(CONTAINER_COLOUR, BorderStrokeStyle.SOLID,
                INPUT_FIELD_CORNER_RADII, INPUT_FIELD_BORDER_WIDTH);
        BorderStroke buttonBorder = new BorderStroke(CONTAINER_COLOUR, BorderStrokeStyle.SOLID,
                BUTTON_CORNER_RADII, BUTTON_BORDER_WIDTH);

        // InputField properties
        this.inputField.setBackground(new Background(inputFieldFill));
        this.inputField.setMinWidth(mainWindow.getMinWidth()
                - (BUTTON_MIN_WIDTH - 2 * CONTAINER_INSET_VALUE - CONTAINER_SPACING_VALUE));
        this.inputField.prefWidthProperty().bind(this.widthProperty());
        this.inputField.setBorder(new Border(inputFieldBorder));

        // SendButton properties
        this.sendButton.setBackground(new Background(buttonFill));
        this.sendButton.setFont(new Font(BUTTON_FONT, BUTTON_FONT_SIZE));
        this.sendButton.setMinWidth(BUTTON_MIN_WIDTH);
        this.sendButton.setBorder(new Border(buttonBorder));

        // InputContainer properties
        this.setBackground(new Background(new BackgroundFill(CONTAINER_COLOUR, CONTAINER_CORNER_RADII, Insets.EMPTY)));
        this.setMinWidth(mainWindow.getMinWidth());
        this.prefWidthProperty().bind(mainWindow.widthProperty());
        this.setPadding(new Insets(CONTAINER_INSET_VALUE));
        this.setSpacing(CONTAINER_SPACING_VALUE);
        this.setAlignment(Pos.CENTER_LEFT);
        this.getChildren().addAll(this.inputField, this.sendButton);
    }

    /**
     * Initializes the event handlers of the user input field and the "send" button.
     * Signals the main window to handle user input when an event is triggered.
     *
     * @param mainWindow the main window to signal when an event is triggered
     * @param chatBot a reference to the chatbot to access logs
     */
    public void initializeEventHandlers(MainWindow mainWindow, ChatBot chatBot) {
        this.inputField.setOnKeyPressed((event) -> {
            if (event.getCode().equals(KeyCode.ENTER)) {
                mainWindow.handleUserInput(this.inputField.getText());
                this.inputField.clear();
            }

            if (chatBot.isRunning()) {
                if (event.getCode().equals(KeyCode.UP)) {
                    this.inputField.clear();
                    this.inputField.setText(chatBot.getPreviousLog());
                    this.inputField.end();
                }
                if (event.getCode().equals(KeyCode.DOWN)) {
                    this.inputField.clear();
                    this.inputField.setText(chatBot.getNextLog());
                    this.inputField.end();
                }
            }
        });
        this.sendButton.setOnMouseClicked((event) -> {
            mainWindow.handleUserInput(this.inputField.getText());
            this.inputField.clear();
        });
    }
}
