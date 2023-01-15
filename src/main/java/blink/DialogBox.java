package blink;

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
import javafx.scene.shape.Circle;

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

    //Styling adapted from https://github.com/Darren12345677/ip/blob/master/src/main/java/roofus/DialogBox.java
    private DialogBox(String text, Image img, String style) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        dialog.setStyle(style);
        dialog.setText(text);
        displayPicture.setImage(img);
        displayPicture.setClip(
                new Circle(
                        50, 45, 45
                )
        );
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
        String styling = "-fx-background-color: bisque; -fx-background-radius: 50;"
                + " -fx-padding: 10;";
        return new DialogBox(text, img, styling);
    }

    public static DialogBox getDukeDialog(String text, Image img) {
        String stlying = "-fx-background-color: lightgreen; -fx-background-radius: 30; "
                + "-fx-padding: 12;";
        var db = new DialogBox(text, img, stlying);
        db.flip();
        return db;
    }
}
