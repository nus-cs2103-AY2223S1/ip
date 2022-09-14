package duke.gui;

import java.io.IOException;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

/**
 * An example of a custom control using FXML.
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class DialogBox extends HBox {

    @FXML
    private Circle circle;

    @FXML
    private Label dialog;

    /**
     * Constructs a DialogBox instance.
     *
     * @param text Text in the dialogue box.
     * @param img Image of the owner of the text.
     */
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
        circle.setFill(new ImagePattern(img));
    }

    /**
     * Flips the dialogue box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
    }

    /**
     * Creates a dialogue box for the user.
     *
     * @param text Text entered by the user.
     * @param img Image of the user.
     * @return A DialogBox instance.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        var dialogBox = new DialogBox(text, img);
        setImageColor(dialogBox, Color.BLUE);
        return dialogBox;
    }

    /**
     * Creates a dialogue box for duke.
     *
     * @param text Text entered by duke.
     * @param img Image of duke.
     * @return A DialogBox instance.
     */
    public static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        setImageColor(db, Color.RED);
        return db;
    }

    /**
     * Sets the surrounding color of the circle image.
     *
     * @param dialogBox  A dialogBox with a circular image.
     * @param color Color at the  edge of the circular image.
     */
    public static void setImageColor(DialogBox dialogBox, Color color) {
        // Solution below adapted from https://www.youtube.com/watch?v=54fEFYx34vk
        dialogBox.circle.setEffect(new DropShadow(+12, 0, 0, color));
    }

}
