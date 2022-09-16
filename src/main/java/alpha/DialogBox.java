package alpha;

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

    /**
     * Constructor to initialise the text to be displayed.
     * @param text Text to be displayed.
     */
    private DialogBox(String text) {
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
     * Sets image to be displayed with the dialog box.
     * @param img Image to be displayed.
     */
    public void setImage(Image img) {
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
     * Returns a Dialog box containing the user's input text.
     * @param text Input text by user.
     * @return Dialog box containing the user's input text.
     */
    public static DialogBox getUserDialog(String text) {
        var db = new DialogBox(text);
        return db;
    }

    /**
     * Returns a Dialog box containing Alpha's response text.
     * @param text Alpha's response to the user's input.
     * @return Dialog box containing Alpha's response text.
     */
    public static DialogBox getAlphaDialog(String text) {
        var db = new DialogBox(text);
        db.flip();
        return db;
    }

    /**
     * Returns a Dialog box containing the error message to be printed in red colour.
     * @param text Error message.
     * @return Dialog box containing the error message to be printed in red colour
     */
    public static DialogBox getErrorDialog(String text) {
        var db = new DialogBox(text);
        db.dialog.setStyle("-fx-text-fill: #fc6060;"
                + "-fx-background-color: rgba(128, 128, 128, 0.50); -fx-background-radius: 7.5;");
        db.flip();
        return db;
    }
}
