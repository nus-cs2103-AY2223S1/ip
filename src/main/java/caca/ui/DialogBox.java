package caca.ui;

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
import javafx.scene.text.Font;

//@@author carriezhengjr-reused
// Reused from https://se-education.org/guides/tutorials/javaFxPart4.html with modifications.

/**
 * An example of a custom control using FXML.
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face
 * and a label containing text from the speaker.
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

            // Solution below adapted from
            // https://www.tabnine.com/code/java/methods/javafx.scene.Node/setStyle
            dialog.setStyle("-fx-background-color: #b9edec;"
                    + "-fx-background-radius: 10;"
                    + "-fx-padding: 10;");

        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);

        // Solution below adapted from
        // https://jenkov.com/tutorials/javafx/text.html
        dialog.setFont(Font.font("Comic Sans MS", 13));

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
     * Gets a dialog box that corresponds to user input.
     *
     * @param text The user input.
     * @param img User image
     * @return DialogBox that corresponds to user input.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }

    /**
     * Gets a dialog box that corresponds to CaCa response.
     *
     * @param text CaCa response
     * @param img CaCa image
     * @return DialogBox that corresponds to CaCa response.
     */
    public static DialogBox getCaCaDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        return db;
    }
}
