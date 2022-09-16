package duke.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.io.IOException;
import java.util.Collections;

/**
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
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
        displayPicture.setClip(new Circle(33, 33, 30));
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
     * Constructs a user dialog with the given text and image.
     * @param text The text to display in the dialog.
     * @param img The user image to display.
     * @return The constructed DialogBox.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        Color col = Color.rgb(25,130,252);
        CornerRadii radii = new CornerRadii(15, 0, 15 ,15, false);
        Background background = new Background(new BackgroundFill(col, radii, Insets.EMPTY));
        db.dialog.setBackground(background);
        return db;
    }

    /**
     * Constructs a Duke dialog with the given text and image.
     * @param text The text to display in the dialog.
     * @param img The Duke image to display.
     * @return The constructed DialogBox.
     */
    public static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        Color col = Color.rgb(67,204,71);
        CornerRadii radii = new CornerRadii(0, 15, 15 ,15, false);
        Background background = new Background(new BackgroundFill(col, radii, Insets.EMPTY));
        db.dialog.setBackground(background);
        db.flip();
        return db;
    }
}