package duke.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * Controller for the dialog box of the Duke application.
 */
public class DialogBox extends HBox {
    @FXML
    private final Label text;
    @FXML
    private final ImageView displayImage;

    /**
     * Private constructor for a dialog box that takes in a label and an image.
     *
     * @param label Label to be shown in the dialog box.
     * @param image Image to be shown in the dialog box.
     */
    private DialogBox(String label, Image image) {
        text = new Label(label);
        displayImage = new ImageView(image);

        text.setWrapText(true);
        displayImage.setFitWidth(100.0f);
        displayImage.setFitHeight(100.0f);

        setAlignment(Pos.TOP_RIGHT);
        getChildren().addAll(text, displayImage);
    }

    /**
     * Flips the dialog box so that the image is to the left of the text.
     */
    private void flip() {
        setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        getChildren().setAll(tmp);
    }

    /**
     * Factory method to create a user dialog box.
     *
     * @param label User's text to be shown.
     * @param image User's image to be shown.
     * @return User dialog box.
     */
    public static DialogBox getUserDialog(String label, Image image) {
        return new DialogBox(label, image);
    }

    /**
     * Factory method to create a duke dialog box.
     *
     * @param label Duke's text to be shown.
     * @param image Duke's image to be shown.
     * @return Duke dialog box.
     */
    public static DialogBox getDukeDialog(String label, Image image) {
        DialogBox dialogBox = new DialogBox(label, image);
        dialogBox.flip();
        return dialogBox;
    }
}
