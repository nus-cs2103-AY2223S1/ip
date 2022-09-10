package components;

import java.io.IOException;
import java.util.Objects;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

/**
 * An example of a custom control using FXML.
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class ErrorDialogBox extends HBox {

    private final Image warningImage = new Image(
        Objects.requireNonNull(this.getClass().getResourceAsStream("/images/warning.png")));

    @FXML
    private Label dialog;
    @FXML
    private Circle displayPicture;

    // We will never initialise ErrorDialogBox with a constructor.
    // Use the static methods instead to get relevant ErrorDialogBox objects.
    private ErrorDialogBox(String text) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/ErrorDialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        dialog.setText(text);
        displayPicture.setFill(new ImagePattern(warningImage));
    }

    /**
     * Gets the dialog box representing text from the error.
     *
     * @param text the text to be displayed
     * @return an ErrorDialogBox object with the given text
     */
    public static ErrorDialogBox getErrorDialog(String text) {
        return new ErrorDialogBox(text);
    }
}
