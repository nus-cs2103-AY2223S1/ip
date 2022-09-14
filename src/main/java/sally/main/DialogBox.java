package sally.main;

import java.io.IOException;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.Node;

/**
 * DialogBox class to represent the dialog box to be shown to the user.
 *
 * @author liviamil
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;

    @FXML
    private ImageView displayedImage;

    /**
     * Constructor for DialogBox parsing in message and image to be displayed
     *
     * @param message to be displayed to dialog box
     * @param image to be displayed beside dialog box
     */
    protected DialogBox(String message, Image image) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(message);
        displayedImage.setImage(image);
    }

    /**
     * Flips the dialog box for sally to set the image on the left of dialog box
     */
    protected void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
    }

    /**
     * Gets the user's dialog
     *
     * @param message user message
     * @param image user image
     * @return new DialogBox made
     */
    public static DialogBox getUserDialog(String message, Image image) {
        return new DialogBox(message, image);
    }

    /**
     * Gets sally's dialog
     *
     * @param message Sally's message
     * @param image sally's image
     * @return new Dialog Box made
     */
    public static DialogBox getSallyDialog(String message, Image image) {
        var db = new DialogBox(message, image);
        db.flip();
        return db;
    }
}
