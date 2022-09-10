package raiden.ui;

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
import javafx.scene.shape.Polygon;

/**
 * @@author HowSuen-reused
 * Reused from https://se-education.org/guides/tutorials/javaFxPart4.html
 *
 * An example of a custom control using FXML.
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;
    @FXML
    private Polygon triangle;

    private DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.dialog.setText(text);
        this.displayPicture.setImage(img);
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
        this.dialog.setStyle("-fx-background-color: #4C0099; -fx-text-fill: white; -fx-label-padding: 5; "
                + "-fx-background-radius: 10;");
        this.triangle.setStyle("-fx-stroke: #4C0099; -fx-fill: #4C0099; -fx-scale-x: -1; -fx-translate-y: 10");
    }

    /**
     * Returns the user's dialog box.
     *
     * @param text The text message associated with the user's dialog.
     * @param img The image of the user.
     * @return The DialogBox of the user's input.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }

    /**
     * Returns Raiden's dialog box.
     *
     * @param text The text message associated with Raiden's response.
     * @param img Raiden's image.
     * @return The DialogBox of Raiden's response.
     */
    public static DialogBox getRaidenDialog(String text, Image img) {
        var dialogBox = new DialogBox(text, img);
        dialogBox.flip();
        return dialogBox;
    }
    // @@author
}
