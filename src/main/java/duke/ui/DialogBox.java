package duke.ui;

import java.io.IOException;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;

//@@author RezwanArefin01-reused
//Adapted from: https://se-education.org/guides/tutorials/javaFxPart4.html#javafx-tutorial-part-4-using-fxml
/**
 * A DialogBox containing an image and a text.
 */
public class DialogBox extends HBox {

    private static final Background USER_BACKGROUND = new Background(
        new BackgroundFill(Color.web("#e5b4c1"), new CornerRadii(20), Insets.EMPTY));
    private static final Background DUKE_BACKGROUND = new Background(
        new BackgroundFill(Color.web("#9fd2dd"), new CornerRadii(20), Insets.EMPTY));

    @FXML
    private TextFlow textFlow;
    @FXML
    private Text text;
    @FXML
    private Circle image;

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
        image.setFill(new ImagePattern(img));
    }

    /**
     * Flips the dialog box such that the {@code Circle} is on the left and text on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
    }

    public static DialogBox getUserDialog(String text, Image img) {
        DialogBox db = new DialogBox(text, img);
        db.textFlow.setBackground(USER_BACKGROUND);
        db.setAlignment(Pos.TOP_RIGHT);
        return db;
    }

    public static DialogBox getDukeDialog(String text, Image img) {
        DialogBox db = new DialogBox(text, img);
        db.flip();
        db.textFlow.setBackground(DUKE_BACKGROUND);
        db.setAlignment(Pos.TOP_LEFT);
        return db;
    }
}
//@@author
