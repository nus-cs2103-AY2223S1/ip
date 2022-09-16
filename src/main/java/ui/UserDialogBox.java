package ui;

import javafx.scene.image.Image;

/**
 * This control represents a dialog box for the user.
 */
public class UserDialogBox extends DialogBox {
    private static final String RESOURCE_NAME = "/view/UserDialogBox.fxml";

    /**
     * Constructs a dialog box for the user with some text and image.
     *
     * @param text The specified text.
     * @param img The specified image.
     */
    public UserDialogBox(String text, Image img) {
        super(text, img, RESOURCE_NAME);
    }
}
