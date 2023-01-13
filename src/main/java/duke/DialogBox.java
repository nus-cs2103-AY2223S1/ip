package duke;

import java.io.IOException;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class DialogBox extends HBox {

    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    private DialogBox(String text, Image img) {
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
        setStyle("-fx-background-color: lightgrey; -fx-background-insets: 5, 5, 5, 5;");
        setAlignment(Pos.CENTER_LEFT);
    }

    private void error() {
        flip();
        setStyle("-fx-background-color: moccasin; -fx-background-insets: 5, 5, 5, 5;");
    }

    /**
     * Returns DialogBox corresponding to the user.
     * @param text String contained in the DialogBox.
     * @param img Profile picture of user.
     * @return DialogBox created using user input.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }

    /**
     * Returns DialogBox corresponding to Duke.
     * @param text String contained in the DialogBox.
     * @param img Profile picture of Duke.
     * @return DialogBox created using Duke input.
     */
    public static DialogBox getDukeDialog(String text, Image img) {
        var dialogBox = new DialogBox(text, img);
        dialogBox.flip();
        return dialogBox;
    }

    public static DialogBox getErrorDialog(String text, Image img) {
        var dialogBox = new DialogBox(text, img);
        dialogBox.error();
        return dialogBox;
    }
}
