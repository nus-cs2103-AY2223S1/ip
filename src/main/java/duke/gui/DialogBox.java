package duke.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.Node;

public class DialogBox extends HBox {
    /** Text accompanying the dialog. */
    private Label text;

    /** Display picture accompanying the dialog. */
    private ImageView displayPicture;

    /**
     * Creates a DialogBox.
     *
     * @param text Text accompanying the dialog.
     * @param image Display picture accompanying the dialog.
     */
    public DialogBox(Label text, ImageView image) {
        text = text;
        displayPicture = image;

        text.setWrapText(true);
        displayPicture.setFitWidth(50.0);
        displayPicture.setFitHeight(50.0);

        this.setAlignment(Pos.TOP_RIGHT);
        this.getChildren().addAll(text, displayPicture);
    }

    /**
     * Returns a user dialog, which floats right.
     *
     * @param text Text accompanying the dialog.
     * @param image Display picture of the user.
     * @return Right-floating dialog box representing the user dialog.
     */
    public static DialogBox getUserDialog(Label text, ImageView image) {
        DialogBox userDialog = new DialogBox(text, image);
        return userDialog;
    }

    /**
     * Returns a duke dialog, which floats left.
     *
     * @param text Text accompanying the dialog.
     * @param image Display picture of the duke chat-bot.
     * @return Left-floating dialog box representing the duke dialog.
     */
    public static DialogBox getDukeDialog(Label text, ImageView image) {
        DialogBox dukeDialog = new DialogBox(text, image);
        dukeDialog.flip();
        return dukeDialog;
    }

    /**
     * Flips the DialogBox horizontally.
     */
    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }
}
