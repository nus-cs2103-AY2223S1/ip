package tuna.gui;

import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.image.Image;

//@@author j-lum-reused
//Reused from https://se-education.org/guides/tutorials/javaFx.html
//with minor modifications
/**
 * An example of a custom control using FXML.
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class TunaDialogBox extends DialogBox {

    private TunaDialogBox(String text, Image img) {
        super(text, img, "/view/TunaDialogBox.fxml");
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

    public static TunaDialogBox getTunaDialog(String text, Image img) {
        var db = new TunaDialogBox(text, img);
        db.flip();
        return db;
    }
}
