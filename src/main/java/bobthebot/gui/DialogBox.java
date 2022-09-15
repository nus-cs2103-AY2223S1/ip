package bobthebot.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class DialogBox extends HBox {

    private Label text;
    private ImageView displayPicture;
    private static final int DISPLAY_PICTURE_WIDTH = 100;
    private static final int DISPLAY_PICTURE_HEIGHT = 100;

    // @@author janelleljt-reused
    // Reused from https://se-education.org/guides/tutorials/javaFx.html
    // with minor modifications
    public DialogBox(Label l, ImageView iv) {
        text = l;
        displayPicture = iv;

        text.setWrapText(true);
        displayPicture.setFitWidth(DISPLAY_PICTURE_WIDTH);
        displayPicture.setFitHeight(DISPLAY_PICTURE_HEIGHT);

        this.setAlignment(Pos.TOP_RIGHT);
        this.getChildren().addAll(text, displayPicture);
    }

    // @@author janelleljt-reused
    // Reused from https://se-education.org/guides/tutorials/javaFx.html
    // with minor modifications
    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }

    // @@author janelleljt-reused
    // Reused from https://se-education.org/guides/tutorials/javaFx.html
    // with minor modifications
    public static DialogBox getUserDialog(Label l, ImageView iv) {
        return new DialogBox(l, iv);
    }

    // @@author janelleljt-reused
    // Reused from https://se-education.org/guides/tutorials/javaFx.html
    // with minor modifications
    public static DialogBox getDukeDialog(Label l, ImageView iv) {
        var db = new DialogBox(l, iv);
        db.flip();
        return db;
    }
}