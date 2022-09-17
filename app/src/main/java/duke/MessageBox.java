package duke;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * A message box to represent communication between the user and Duke.
 */
public class MessageBox extends HBox {
    private Label text;
    private ImageView displayPicture;

    /**
     * Creates a new MessageBox.
     * @param l The label to use on the MessageBox.
     * @param iv The ImageView of the MessageBox.
     */
    public MessageBox(Label l, ImageView iv) {
        text = l;
        displayPicture = iv;

        text.setWrapText(true);
        displayPicture.setFitWidth(100.0);
        displayPicture.setFitHeight(100.0);

        this.setAlignment(Pos.TOP_RIGHT);
        this.getChildren().addAll(text, displayPicture);
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
     * Get a MessageBox representing the user's messages.
     * @param l The label to use on the MessageBox.
     * @param iv The ImageView of the MessageBox.
     * @return The MessageBox representing the user's messages.
     */
    public static MessageBox getUserDialog(Label l, ImageView iv) {
        return new MessageBox(l, iv);
    }

    /**
     * Get a MessageBox representing Duke's messages.
     * @param l The label to use on the MessageBox.
     * @param iv The ImageView of the MessageBox.
     * @return The MessageBox representing Duke's messages.
     */
    public static MessageBox getDukeDialog(Label l, ImageView iv) {
        var db = new MessageBox(l, iv);
        db.flip();
        return db;
    }
}
