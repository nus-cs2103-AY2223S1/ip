package duke.ui;

import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;

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

    private DialogBox(String s, Image img) {
        dialog = new Label(s);
        dialog.setFont(new Font("SF Mono Medium", 9));
        displayPicture = new ImageView(img);

        dialog.setWrapText(true);
        displayPicture.setFitWidth(100.0);
        displayPicture.setFitHeight(150.0);

        this.setAlignment(Pos.TOP_RIGHT);
        this.getChildren().addAll(dialog, displayPicture);
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

    public static DialogBox getUserDialog(String s, Image img) {
        return new DialogBox(s, img);
    }

    public static DialogBox getDukeDialog(String s, Image img) {
        var db = new DialogBox(s, img);
        db.flip();
        return db;
    }
}