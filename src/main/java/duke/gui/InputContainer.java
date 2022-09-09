package duke.gui;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class InputContainer extends HBox {
    private TextField userInputField;
    private Button sendButton;

    /**
     * Creates a container to contain the input text field and send button.
     */
    public InputContainer() {
        this.userInputField = new TextField();
        this.sendButton = new Button("Send");

        this.userInputField.setPrefWidth(325.0);
        this.sendButton.setPrefWidth(55.0);

        this.setAlignment(Pos.CENTER_LEFT);
        this.getChildren().addAll(this.userInputField, this.sendButton);
    }

    /**
     * Initializes the event handlers of the user input field and the send button.
     * Signals the main window to handle user input when an event is triggered.
     *
     * @param mainWindow the main window to signal when an event is triggered
     */
    public void initializeEventHandlers(MainWindow mainWindow) {
        this.userInputField.setOnAction((event) -> {
            mainWindow.handleUserInput(this.userInputField.getText());
            this.userInputField.clear();
        });
        this.sendButton.setOnMouseClicked((event) -> {
            mainWindow.handleUserInput(this.userInputField.getText());
            this.userInputField.clear();
        });
    }
}
