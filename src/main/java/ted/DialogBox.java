package ted;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.TextFlow;
import ted.ui.MainWindow;

/**
 * An example of a custom control using FXML.
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class DialogBox extends VBox {
    @FXML
    private HBox profile;
    @FXML
    private Label name;
    @FXML
    private TextFlow dialogWrapper;
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

    public static DialogBox getUserDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.setAlignment(Pos.TOP_RIGHT);
        db.profile.setAlignment(Pos.CENTER_RIGHT);
        db.dialog.setStyle("-fx-background-radius: 15px 0px 15px 15px; -fx-background-color: rgb(195, 226, 250);");
        db.name.setText("You");
        return db;
    }

    public static DialogBox getTedDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.dialog.setStyle("-fx-background-radius: 0px 15px 15px 15px; -fx-background-color: white;");
        db.name.setText("Ted");
        return db;
    }

    public static DialogBox getErrorDialog(String text, Image img) {
        DialogBox db = getTedDialog(text, img);
        db.dialog.setStyle("-fx-background-radius: 0px 15px 15px 15px; -fx-background-color: rgb(252, 104, 78);");
        db.dialog.setTextFill(Color.WHITE);
        return db;
    }
}
