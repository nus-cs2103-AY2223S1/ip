package ui;

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

import java.io.IOException;

/**
 * <h1>DialogBox class</h1>
 * A HBox that represents a Dialog containing text and
 * an ImageView.
 */
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
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }

    /**
     * Factory method to create a DialogBox object that represents the user's dialog.
     *
     * @param l Label to be contained within the DialogBox.
     * @param iv ImageView to be contained within the DialogBox.
     * @return the DialogBox object that represents the user's dialog.
     */
    public static DialogBox getUserDialog(String l, Image iv) {
        DialogBox dialogBox = new DialogBox(l, iv);
        dialogBox.displayPicture.setClip(new Circle(47, 52, 40));
        return dialogBox;
    }

    /**
     * Factory method to create a DialogBox object that represents Uncle Cheong's dialog.
     *
     * @param l Label to be contained within the DialogBox.
     * @param iv ImageView to be contained within the DialogBox.
     * @return the DialogBox object that represents the Uncle Cheong's dialog.
     */
    public static DialogBox getUncleCheongDialog(String l, Image iv) {
        DialogBox dialogBox = new DialogBox(l, iv);
        dialogBox.flip();
        dialogBox.displayPicture.setClip(new Circle(49.5, 49.5, 35));
        return dialogBox;
    }
}
