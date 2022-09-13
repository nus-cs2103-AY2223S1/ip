package duke;
import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * An example of a custom control using FXML.
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class DialogBoxUser extends HBox {
    @FXML
    private Label dialogUser;
    @FXML
    private ImageView displayPictureUser;

    private DialogBoxUser(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBoxUser.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialogUser.setText(text);
        displayPictureUser.setImage(img);
    }

    public static DialogBoxUser getUserDialog(String text, Image img) {
        return new DialogBoxUser(text, img);
    }
}
