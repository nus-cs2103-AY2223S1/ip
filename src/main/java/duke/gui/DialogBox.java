package duke.gui;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;

/**
 * An example of a custom control using FXML.
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    private DialogBox(String text, Image img, String name) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource(name));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);
        displayPicture.setImage(img);
        dialog.setMinHeight(Region.USE_PREF_SIZE);
    }


    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img, "/view/UserDialogBox.fxml");
    }

    public static DialogBox getDukeDialog(String text, Image img) {
        return new DialogBox(text, img, "/view/DukeDialogBox.fxml");
    }
}
