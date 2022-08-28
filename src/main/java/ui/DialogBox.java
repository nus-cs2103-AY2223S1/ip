package ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * <h1>DialogBox class</h1>
 * A HBox that represents a Dialog containing text and
 * an ImageView.
 */
public class DialogBox extends HBox {
    private Label text;
    private ImageView displayPicture;

    /**
     * Creates the DialogBox object.
     *
     * @param l Label to be contained within the DialogBox.
     * @param iv ImageView to be contained within the DialogBox.
     */
    public DialogBox(Label l, ImageView iv) {
        text = l;
        displayPicture = iv;

        text.setWrapText(true);
        displayPicture.setFitWidth(100.0);
        displayPicture.setFitHeight(100.0);

        this.setAlignment(Pos.TOP_RIGHT);
        this.getChildren().addAll(text, displayPicture);
        this.setPadding(new Insets(10, 0, 10, 0));
        //this.setBackground(new Background(new BackgroundFill(Color.BEIGE, CornerRadii.EMPTY, Insets.EMPTY)));
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
     * Factory method to create a DialogBox object that represents the user's dialog.
     *
     * @param l Label to be contained within the DialogBox.
     * @param iv ImageView to be contained within the DialogBox.
     * @return the DialogBox object that represents the user's dialog.
     */
    public static DialogBox getUserDialog(Label l, ImageView iv) {
        return new DialogBox(l, iv);
    }

    /**
     * Factory method to create a DialogBox object that represents Uncle Cheong's dialog.
     *
     * @param l Label to be contained within the DialogBox.
     * @param iv ImageView to be contained within the DialogBox.
     * @return the DialogBox object that represents the Uncle Cheong's dialog.
     */
    public static DialogBox getUncleCheongDialog(Label l, ImageView iv) {
        var db = new DialogBox(l, iv);
        db.flip();
        return db;
    }
}
