package ui;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

/**
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class DialogBox extends HBox {
    @FXML
    private HBox speechBubble;
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    /**
     * Constructs a new DialogBox with some text, image, and a resource name which indicates the FXML file to
     * be used.
     *
     * @param text The specified text.
     * @param img The specified image.
     * @param resourceName The specified resource name.
     */
    protected DialogBox(String text, Image img, String resourceName) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource(resourceName));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Add styling to dialog box
        this.dialog.setText(text);
        this.dialog.setFont(Font.font(java.awt.Font.MONOSPACED, FontWeight.BOLD,
                FontPosture.REGULAR, 12));
        this.dialog.setMinHeight(Region.USE_PREF_SIZE);

        // Add styling to dialog display picture
        this.displayPicture.setImage(img);
        this.displayPicture.setClip(new Circle(25, 25, 25));
    }
}
