package duke.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * The UI component that is responsible for displaying outputs to the user.
 */
public class DialogBox extends HBox {

    /**
     * Constructs a component to display output, containing an image and text.
     *
     * @param label label containing text
     * @param view image to show
     */
    public DialogBox(Label label, ImageView view) {
        label.setWrapText(true);
        view.setFitWidth(100.0);
        view.setFitHeight(100.0);

        this.setAlignment(Pos.TOP_RIGHT);
        this.getChildren().addAll(label, view);
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

    public static DialogBox getUserDialog(Label label, ImageView view) {
        return new DialogBox(label, view);
    }

    public static DialogBox getDukeDialog(Label label, ImageView view) {
        var db = new DialogBox(label, view);
        db.flip();
        return db;
    }
}
