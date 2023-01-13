package kirby.ui;

import java.io.IOException;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

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

    /**
     * Constructor for DialogBox.
     * @param text String contained in DialogBox. Could be input or output.
     * @param img Profile picture.
     */
    DialogBox(String text, Image img, String name) {
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
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
    }

    /**
     * Displays the user Dialog.
     *
     * @param text String to be displayed as what user said.
     * @param img Profile picture of user.
     * @return DialogBox of what user said and avatar.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img, "/view/UserDialogBox.fxml");
    }

    /**
     * Displays Kirby's Dialog.
     *
     * @param text String to be displayed as what Kirby said.
     * @param img Profile picture of Kirby.
     * @return DialogBox of what Kirby said and avatar.
     */
    public static DialogBox getKirbyDialog(String text, Image img) {
        var db = new DialogBox(text, img, "/view/KirbyDialogBox.fxml");
        db.flip();
        return db;
    }
}
