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
import javafx.scene.layout.Region;

/** Class to represent the dialog bubble of the user and the bot. */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;

    @FXML
    private ImageView displayPicture;

    /**
     * DialogBox constructor.
     *
     * @param text The text to be inserted in the dialog box.
     * @param img The avatar of the dialog box.
     */
    private DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
            dialog.setText(text);
            dialog.setMinHeight(Region.USE_PREF_SIZE);
            displayPicture.setImage(img);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /** Flips the dialog box such that the ImageView is on the left and text on the right.*/
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
    }

    /**
     * Returns a DialogBox from the user.
     *
     * @param text The text to be inserted in the dialog box.
     * @param img The avatar of the dialog box.
     * @return A DialogBox from the user.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }

    /**
     * Returns a DialogBox from the bot.
     *
     * @param text The text to be inserted in the dialog box.
     * @param img The avatar of the dialog box.
     * @return A DialogBox from the bot.
     */
    public static DialogBox getAvaDialog(String text, Image img) {
        DialogBox db = new DialogBox(text, img);
        db.flip();
        return db;
    }
}
