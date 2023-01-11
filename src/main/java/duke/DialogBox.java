package duke;

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

import java.io.IOException;
import java.util.Collections;


/**
 * An example of a custom control using FXML.
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class DialogBox extends HBox{

    @FXML
    private Label dialog;

    @FXML
    private ImageView displayPicture;

    private DialogBox(String txt, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(duke.MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();

        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(txt);
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
     * Creates a DialogBox that belongs to the user.
     *
     * @param txt the text shown in the dialog box.
     * @param img the image shown in the dialog box.
     * @return
     */
    public static DialogBox getUserDialog(String txt, Image img) {
        return new DialogBox(txt, img);
    }

    /**
     * Creates a DialogBox that belongs to duke.
     *
     * @param txt the text shown in the dialog box.
     * @param img the image shown in the dialog box.
     * @return
     */
    public static DialogBox getDukeDialog(String txt, Image img) {
        var db = new DialogBox(txt, img);
        db.flip();
        return db;
    }
}
