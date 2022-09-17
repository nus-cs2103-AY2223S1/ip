package duke.data;

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

    private final String DUKESTYLE = "-fx-background-color: #33CC99;"
            + "-fx-font: 12px Tahoma;"
            + "-fx-text-fill: white;"
            + "-fx-min-width: 240px;"
            + "-fx-background-radius: 25.0;"
            + "-fx-padding: 20.0";

    private final String USERSTYLE =  "-fx-background-color: #FF88CC;"
            + "-fx-font: 12px Tahoma;"
            + "-fx-text-fill: white;"
            + "-fx-min-width: 240px;"
            + "-fx-background-radius: 25.0;"
            + "-fx-padding: 20.0";

    /**
     * Constructor for a DialogBox.
     * @param text Input to be printed on the DialogBox.
     * @param img Image of the user.
     */
    public DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
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

    public static DialogBox getUserDialog(String text, Image img) {
        DialogBox db = new DialogBox(text, img);
        db.dialog.setStyle(db.USERSTYLE);
        return db;
    }

    public static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.dialog.setStyle(db.DUKESTYLE);
        db.flip();
        return db;
    }
}
