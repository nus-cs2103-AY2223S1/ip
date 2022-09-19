package main;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * Represents the text boxes that represents individual dialogue bubbles from either user or Duke.
 */
public class DialogBox extends HBox {

    private Label text;
    private ImageView displayPicture;

    private DialogBox(Label l, ImageView iv) {
        text = l;
        displayPicture = iv;

        text.setWrapText(true);
        displayPicture.setFitWidth(100.0);
        displayPicture.setFitHeight(100.0);

        this.setAlignment(Pos.TOP_RIGHT);
        this.getChildren().addAll(text, displayPicture);
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

    /**
     * Retrieves the DialogBox from the User to be printed with the given text and image.
     *
     * @param label Represents the text that is input by the User.
     * @param imageView Represents the Image that represents the User.
     * @return DialogBox The object to be printed with the given text and image.
     */
    public static DialogBox getUserDialog(Label label, ImageView imageView) {
        return new DialogBox(label, imageView);
    }

    /**
     * Retrieves the DialogBox from Duke to be printed with the given text and image.
     *
     * @param label Represents the text that is input by Duke.
     * @param imageView Represents the Image that represents Duke.
     * @return DialogBox The object to be printed with the given text and image.
     */
    public static DialogBox getDukeDialog(Label label, ImageView imageView) {
        var dialogBox = new DialogBox(label, imageView);
        dialogBox.flip();
        return dialogBox;
    }
}
