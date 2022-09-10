package components;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;

/**
 * An example of a custom control using FXML.
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class UserDialogBox extends HBox {
    @FXML
    private Label dialog;

    // We will never initialise HenryDialogBox with a constructor.
    // Use the static methods instead to get relevant HenryDialogBox objects.
    private UserDialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/UserDialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        dialog.setText(text);
    }

    /**
     * Gets the dialog box representing text from the user.
     *
     * @param text the text to be displayed
     * @param img  the image to be displayed
     * @return a HenryDialogBox object with the given text and image
     */
    public static UserDialogBox getUserDialog(String text, Image img) {
        return new UserDialogBox(text, img);
    }
}
