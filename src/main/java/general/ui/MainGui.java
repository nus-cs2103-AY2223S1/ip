package general.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import mia.Mia;

public class MainGui extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox chatWindow;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Mia context;

    private final Image userImage = new Image(
            Objects.requireNonNull(getClass().getResourceAsStream("/avatars/user.png")));
    private final Image botImage = new Image(
            Objects.requireNonNull(getClass().getResourceAsStream("/avatars/bot.png")));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(chatWindow.heightProperty());
    }

    public void setContext(Mia context) {
        this.context = context;
    }

    @FXML
    private void handleInput() {
        final String inputText = userInput.getText();
        final ArrayList<ChatBubble> bubbles = new ArrayList<>();
        bubbles.add(ChatBubble.fromCommand(inputText, userImage));
        final List<String> responses = context.parseAndExecute(inputText);
        for (int i = 0; i < responses.size(); i++) {
            bubbles.add(ChatBubble.fromResponse(responses.get(i), botImage));
        }
        chatWindow.getChildren().addAll(bubbles);
        userInput.clear();
        if (context.shouldExitExternalContext()) {
            Platform.exit();
        }
    }
}
