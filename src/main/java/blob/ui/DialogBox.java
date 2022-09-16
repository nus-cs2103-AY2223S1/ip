package blob.ui;

import java.io.IOException;
import java.util.Arrays;

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
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

/**
 * The DialogBox class represents the container in which messages are displayed in the GUI.
 */
public class DialogBox extends HBox {

    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    /**
     * Constructs a dialog box using the given display image to represent user/application
     * messages as given.
     *
     * @param img The given display image.
     * @param text The given list of messages.
     */
    public DialogBox(Image img, String ...text) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setFont(Font.font("Georgia", FontWeight.SEMI_BOLD, FontPosture.REGULAR, 14));
        dialog.setText(Arrays.stream(text).reduce("", (res, line) -> res + line + "\n"));
        displayPicture.setImage(img);
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }

    public static DialogBox getUserDialog(Image img, String text) {
        return new DialogBox(img, text);
    }

    public static DialogBox getBlobDialog(Image img, String ...text) {
        var db = new DialogBox(img, text);
        db.flip();
        return db;
    }
}
