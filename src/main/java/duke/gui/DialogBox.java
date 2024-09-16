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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * Class containing the DialogBox.
 * Contains and display the picture of Duke and user as well as their dialog.
 */
public class DialogBox extends HBox {

    private static final String[] dukeStyleClasses = {"duke-dialog-background"};
    private static final String[] userStyleClasses = {"user-dialog-background"};

    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    private DialogBox(String text, Image img, String... styleCLasses) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);
        dialog.getStyleClass().addAll(styleCLasses);
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
     * Returns the DialogBox of user.
     *
     * @param text Text of user.
     * @param img Image of user.
     * @return The DialogBox of user.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img, userStyleClasses);
    }

    /**
     * Returns the DialogBox of Duke.
     *
     * @param text Response of Duke.
     * @param img Image of Duke.
     * @return The DialogBox of Duke.
     */
    public static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox(text, img, dukeStyleClasses);
        db.flip();
        return db;
    }
}
