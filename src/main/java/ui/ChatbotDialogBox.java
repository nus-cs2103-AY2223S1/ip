package ui;

import javafx.geometry.Pos;
import javafx.scene.image.Image;

/**
 * This control represents a dialog box for the chatbot.
 */
public class ChatbotDialogBox extends DialogBox {
    private static final String RESOURCE_NAME = "/view/ChatbotDialogBox.fxml";

    /**
     * Constructs a dialog box for the chatbot with some text and image. The alignment will always be to the left,
     * which is opposite of the user's dialog box.
     *
     * @param text The specified text.
     * @param img The specified image.
     */
    public ChatbotDialogBox(String text, Image img) {
        super(text, img, RESOURCE_NAME);

        this.setAlignment(Pos.TOP_LEFT);
    }
}
