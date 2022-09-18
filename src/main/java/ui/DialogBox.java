package ui;

import java.io.IOException;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


/**
 * An example of a custom control using FXML.
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class DialogBox extends HBox {
    private static final int IMAGE_BORDER_SIZE = 100;
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    private DialogBox(String text, Image img, boolean isUser) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);
        String styleClass = isUser ? "user" : "bot";
        // Solution adapted from:
        // https://stackoverflow.com/questions/44404869/how-to-create-and-add-a-style-class-dynamically-in-javafx
        dialog.getStyleClass().add(styleClass);
        displayPicture.setImage(img);
        addRoundedBorder();
    }

    /**
     * Adds rounded border to displayPicture
     */
    private void addRoundedBorder() {
        //@@author huzaifa1712-reused
        // Re-used from
        // https://stackoverflow.com/questions/44404869/how-to-create-and-add-a-style-class-dynamically-in-javafx
        // with minor modifications
        // set a clip to apply rounded border to the original image.
        Rectangle clip = new Rectangle(
                displayPicture.getFitWidth(), displayPicture.getFitHeight()
        );
        clip.setArcWidth(DialogBox.IMAGE_BORDER_SIZE);
        clip.setArcHeight(DialogBox.IMAGE_BORDER_SIZE);
        displayPicture.setClip(clip);

        // snapshot the rounded image.
        SnapshotParameters parameters = new SnapshotParameters();
        parameters.setFill(Color.TRANSPARENT);
        WritableImage image = displayPicture.snapshot(parameters, null);

        // remove the rounding clip so that our effect can show through.
        displayPicture.setClip(null);

        // apply a shadow effect.
        displayPicture.setEffect(new DropShadow(2, Color.BLACK));

        // store the rounded image in the imageView.
        displayPicture.setImage(image);
        //@@author

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
     * Returns DialogBox corresponding to the user
     * @param text Text to display
     * @param img Image to use
     * @return DialogBox
     */
    public static DialogBox getUserDialog(String text, Image img) {
        DialogBox dialog = new DialogBox(text, img, true);
        return dialog;
    }

    /**
     * Returns DialogBox corresponding to the bot
     * @param text Text to display
     * @param img Image to use
     * @return DialogBox
     */
    public static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox(text, img, false);
        db.flip();
        return db;
    }
}
