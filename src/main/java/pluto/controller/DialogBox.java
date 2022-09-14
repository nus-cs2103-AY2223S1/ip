package pluto.controller;

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
import pluto.Ui;

/**
 * This control represents a dialog box consisting of an
 * ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class DialogBox extends HBox {

    /** Text displayed */
    @FXML
    private Label dialog;
    /** Image of the user or Pluto */
    @FXML
    private ImageView displayPicture;

    /**
     * Initializes global variables.
     * @param text Text input or output.
     */
    public DialogBox(String text) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        dialog.setText(text);
    }
    /**
     * Sets image of DialogBox.
     * @param img Image to be set.
     */
    public void setDialogImage(Image img) {
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
     * Returns DialogBox for user.
     * @param text Input given by the user.
     * @return DialogBox generated.
     */
    public static DialogBox getUserDialog(String text) {
        var db = new DialogBox(text);
        db.dialog.setStyle("-fx-text-fill: #FFFFFF; -fx-background-color: rgba(128, 128, 128, 0.50);"
                + " -fx-background-radius: 7.5;");
        return db;
    }

    /**
     * Returns DialogBox for Pluto.
     * @param text Response generated by Pluto.
     * @return DialogBox generated.
     */
    public static DialogBox getPlutoDialog(String text) {
        var db = new DialogBox(text);
        db.flip();
        return db;
    }

    /**
     * Returns Error DialogBox for Pluto.
     * @param text Error response generated by Pluto.
     * @return Error DialogBox generated.
     */
    public static DialogBox getPlutoErrorDialog(String text) {
        var db = new DialogBox(text);
        db.dialog.setStyle("-fx-text-fill: #cc3300; -fx-background-color: rgba(230, 230, 223, 1);"
                + " -fx-background-radius: 7.5; -fx-font-weight: bold;");
        db.flip();
        return db;
    }

    /**
     * Returns whether response is exit message.
     * @return If response is exit message.
     */
    public boolean isExitDialogBox() {
        return dialog.getText().equals(Ui.exitMessage());
    }
}
