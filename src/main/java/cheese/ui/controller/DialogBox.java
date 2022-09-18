package cheese.ui.controller;

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
 * Controls dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class DialogBox extends HBox {

    /** Label containing text from speaker. */
    @FXML
    private Label dialog;

    /** Image to represent speaker's face. */
    @FXML
    private ImageView displayPicture;

    /**
     * Constructs a <code>DialogBox</code> from FXML file with given text and image.
     *
     * @param text Text from speaker.
     * @param img  Image of speaker.
     */
    private DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader =
                    new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
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
     * Returns an instance of <code>DialogBox</code> from the user.
     *
     * @param text Text from user input.
     * @param img  Image to represent user.
     * @return A <code>DialogBox</code> from the user with the given text and image.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }

    /**
     * Returns an instance of <code>DialogBox</code> from Cheese.
     *
     * @param text Response from Cheese.
     * @param img  Image to represent Cheese.
     * @return A <code>DialogBox</code> from Cheese with the given text and image.
     */
    public static DialogBox getCheeseDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        return db;
    }
}
