package duke;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * A message box to represent communication between the user and Duke.
 */
public class MessageBox extends HBox {
    private Label text;
    private ImageView displayPicture;
    private final Image picture = new Image(this.getClass().getResourceAsStream("/catavatar1.png"));

    /**
     * Creates a new MessageBox.
     * @param t The text to use in the MessageBox.
     */
    public MessageBox(String t) {
        text = new Label(t);
        displayPicture = new ImageView(picture);

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
     * @param text The text to use in the MessageBox
     * @return The MessageBox representing the user's messages.
     */
    public static MessageBox getUserDialog(String text) {
        return new MessageBox(text);
    }

    /**
     * Get a MessageBox representing Duke's messages.
     * @param text The text to use in the MessageBox
     * @return The MessageBox representing Duke's messages.
     */
    public static MessageBox getDukeDialog(String text) {
        var db = new MessageBox(text);
        db.flip();
        return db;
    }
}
