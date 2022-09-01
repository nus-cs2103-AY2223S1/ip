package duke.ui;

import java.io.IOException;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;

//@@author RezwanArefin01-reused
//Source: https://se-education.org/guides/tutorials/javaFxPart4.html#javafx-tutorial-part-4-using-fxml
/**
 * A DialogBox containing an image and a text.
 */
public class DialogBox extends HBox {

    @FXML
    private TextFlow textFlow;
    @FXML
    private Text text;
    @FXML
    private ImageView displayPicture;

    /**
     * Creates a new DialogBox with the given message and image.
     *
     * @param message The message to display.
     * @param img The image to display.
     */
    private DialogBox(String message, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        text.setText(message);
        displayPicture.setImage(img);
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
    }

    public static DialogBox getUserDialog(String text, Image img) {
        DialogBox db = new DialogBox(text, img);
        db.setAlignment(Pos.TOP_RIGHT);
        db.textFlow.setTextAlignment(TextAlignment.RIGHT);
        return db;
    }

    public static DialogBox getDukeDialog(String text, Image img) {
        DialogBox db = new DialogBox(text, img);
        db.flip();
        db.setAlignment(Pos.TOP_LEFT);
        db.textFlow.setTextAlignment(TextAlignment.LEFT);
        return db;
    }
}
//@@author
