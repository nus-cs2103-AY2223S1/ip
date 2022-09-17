//@@author lpohsien-reused
//Adapted from https://se-education.org/guides/tutorials/javaFxPart4.html
// with minor changes.
package Duke.Gui;
import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.shape.Circle;

/**
 * An example of a custom control using FXML.
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;
    @FXML
    private HBox dialogHBox;
    @FXML
    private ScrollPane dialogScrollPane;

    private final double imgRadius = 40.0;
    private Circle circle = new Circle(imgRadius, imgRadius, imgRadius);

    @FXML
    public void initialize() {
        if (dialogScrollPane == null) {
            return;
        }
        dialogScrollPane.setFitToWidth(true);
        dialogScrollPane.setOnMouseEntered(e -> {
            dialogScrollPane.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
        });
        dialogScrollPane.setOnMouseExited(e -> {
            dialogScrollPane.setVbarPolicy(ScrollBarPolicy.NEVER);
        });
    }

    private DialogBox(String text, Image img, boolean isUser) {
        try {
            FXMLLoader fxmlLoader;
            if (isUser) {
                fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            } else {
                fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBoxDuke.fxml"));
            }

            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);
        displayPicture.setImage(img);
        displayPicture.setClip(circle);
    }

    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img, true);
    }

    public static DialogBox getDukeDialog(String text, Image img) {
        return new DialogBox(text, img, false);
    }
}
//@@author