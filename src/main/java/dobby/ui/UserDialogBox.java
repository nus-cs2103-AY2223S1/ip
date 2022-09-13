package dobby.ui;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.TextFlow;

/**
 * This control represents a dialog box consisting of an ImageView to represent the speaker's
 * face and a label containing text from the speaker.
 *
 * <p> Adapted from <a href="https://se-education.org/guides/tutorials/javaFxPart4.html">se-education</a> </p>
 */
public class UserDialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;
    @FXML
    private TextFlow textFlow;

    /**
     * Creates a dialog box with the given text and image.
     *
     * @param text the text to be displayed in the dialog box
     * @param img the image to be displayed in the dialog box
     */
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
        displayPicture.setImage(img);
    }

    /**
     * Creates a dialog box for the user.
     *
     * @param text the text to be displayed
     * @param img the image to be displayed
     * @return a dialog box for the user
     */
    public static UserDialogBox getUserDialog(String text, Image img) {
        return new UserDialogBox(text, img);
    }
}
