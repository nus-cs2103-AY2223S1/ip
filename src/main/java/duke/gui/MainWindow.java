package duke.gui;

import duke.chatbot.ChatBot;
import javafx.scene.layout.AnchorPane;

/**
 * MainWindow is an AnchorPane that anchors the various containers present in the main application.
 */
public class MainWindow extends AnchorPane {
    private DialogContainer dialogContainer;
    private InputContainer userInputContainer;
    private ChatBot chatBot;

    /**
     * Creates a main window to contain the dialog container and the user input container.
     *
     * @param chatBot the chatbot that interacts with the user
     */
    public MainWindow(ChatBot chatBot) {
        this.chatBot = chatBot;
        this.dialogContainer = new DialogContainer();
        this.userInputContainer = new InputContainer();
        this.getChildren().addAll(this.dialogContainer, this.userInputContainer);

        this.setTopAnchor(dialogContainer, 1.0);
        this.setBottomAnchor(userInputContainer, 1.0);
    }

    /**
     * Initializes the main window by initializing the chatbot, initializing event handlers
     * for the input container and displaying and initialization response.
     */
    public void initialize() {
        this.chatBot.initialize();
        this.dialogContainer.initializeDialog(this.chatBot.getLatestResponse());
        this.userInputContainer.initializeEventHandlers(this, this.chatBot);
    }

    /**
     * Handles the input given by the user and updates the dialog container accordingly.
     *
     * @param userInput String of the user input
     */
    public void handleUserInput(String userInput) {
        if (this.chatBot.isRunning()) {
            this.chatBot.processCommand(userInput);
            this.dialogContainer.updateDialog(userInput, this.chatBot.getLatestResponse());
        } else {
            System.exit(0);
        }
    }
}
