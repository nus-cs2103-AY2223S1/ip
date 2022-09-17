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
import javafx.scene.layout.HBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

/**
 * An example of a custom control using FXML. This control represents a dialog box consisting of an
 * ImageView to represent the speaker's face and a label containing text from the speaker.
 */
public class DialogBox extends HBox {

    private static final Font UBUNTU_MONO_FONT =
            Font.loadFont(DialogBox.class.getResource("/fonts/UbuntuMono-Regular.ttf").toString(), 15);

    private static final String WARNING_COLOR = "-fx-background-color: #EF3D59;"
            + "-fx-effect: dropshadow(three-pass-box, rgba(0, 0, 0, 0.8), 10, 0, 0, 0);" + "-fx-background-radius:10";

    @FXML
    private Label dialog;
    @FXML
    private Circle displayPicture;

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
        dialog.setFont(UBUNTU_MONO_FONT);
        displayPicture.setFill(new ImagePattern(img));
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
        dialog.setTextAlignment(TextAlignment.LEFT);
    }

    private void setWarning() {
        dialog.setStyle(WARNING_COLOR);
    }

    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }

    public static DialogBox getDukeDialog(String text, Image img) {
        return getDukeDialog(text, img, false);
    }

    public static DialogBox getDukeDialog(String text, Image img, boolean isWarning) {
        DialogBox db = new DialogBox(text, img);
        db.flip();
        if (isWarning) {
            db.setWarning();
        }
        return db;
    }
}
