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
 * An example of a custom control using FXML.
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class DialogBox extends HBox {
    private static final String DUKE_DIALOG_CSS =
            //CHECKSTYLE.OFF: LineLength
            "-fx-shape: 'M66.1 1.5C30.4 1.5 1.5 22.9 1.5 46c0 18.1 17.9 33.5 42.8 39.3 1.5 14.8-1.3 39-8.5 48.1 10.8-12.5 22.4-33.6 26.6-45.7 1.2 0 2.5.1 3.7.1 35.7 0 64.6-18.7 64.6-41.8S101.8 1.5 66.1 1.5zM35.8 133.4c-.3.4-.7.8-1 1.1.4-.3.7-.7 1-1.1z';"
            //CHECKSTYLE.ON: LineLength
            + "\n-fx-background-color: white;"
            + "\n-fx-border-width: 4;"
            + "\n-fx-border-color: black;"
            + "\n-fx-padding: 5 50 50 50;";

    private static final String USER_DIALOG_CSS =
            //CHECKSTYLE.OFF: LineLength
            "-fx-shape: 'M445.9 1.5C481.6 1.5 510.5 22.9 510.5 46c0 18.1-17.9 33.5-42.8 39.3-1.5 14.8 1.3 39 8.5 48.1-10.8-12.5-22.4-33.6-26.6-45.7-1.2 0-2.5.1-3.7.1-35.7 0-64.6-18.7-64.6-41.8S410.2 1.5 445.9 1.5zM476.2 133.4c.3.4 .7.8 1 1.1-.4-.3-.7-.7-1-1.1z';"
                    //CHECKSTYLE.ON: LineLength
                    + "\n-fx-background-color: white;"
                    + "\n-fx-border-width: 4;"
                    + "\n-fx-border-color: black;"
                    + "\n-fx-padding: 5 50 50 50;";

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

    public static DialogBox getUserDialog(String text, Image img) {
        DialogBox result = new DialogBox(text, img);
        Node label = result.getChildren().get(0);
        label.setStyle(USER_DIALOG_CSS);
        return result;
    }

    public static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        Node label = db.getChildren().get(0);
        label.setStyle(DUKE_DIALOG_CSS);
        db.flip();
        return db;
    }
}
