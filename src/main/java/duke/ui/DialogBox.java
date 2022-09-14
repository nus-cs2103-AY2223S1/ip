package duke.ui;
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

    private DialogBox(String text, Image img, String style) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);
        dialog.setStyle(style);
        dialog.setMinSize(10, 10);
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
        String styleForDialog = "-fx-background-color: #0093F9; -fx-background-radius: 10; -fx-padding: 10;"
                + "-fx-font: 13px Tahoma;"
                + "-fx-text-fill: #FFFFFF;";
        DialogBox dialogBox = new DialogBox(text, img, styleForDialog);
        return dialogBox;
    }

    public static DialogBox getDukeDialog(String text, Image img) {
        String styleForDialog = "-fx-background-color:#EEEBEB; -fx-background-radius: 10; -fx-padding: 10;"
                + "-fx-font: 13px Tahoma;"
                + "-fx-text-fill: black;";
        DialogBox dialogBox = new DialogBox(text, img, styleForDialog);
        dialogBox.flip();
        return dialogBox;
    }

    public static DialogBox getDukeErrorDialog(String text, Image img) {
        String styleForDialog = "-fx-background-color:#EEEBEB; -fx-background-radius: 10; -fx-padding: 10;"
                + "-fx-font: 13px Tahoma;"
                + "-fx-text-fill: red;";
        DialogBox dialogBox = new DialogBox(text, img, styleForDialog);
        dialogBox.flip();
        return dialogBox;
    }
}
