package duke.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

/**
 * The UI component that is responsible for displaying outputs to the user.
 */
public class DialogBox extends HBox {

    /**
     * Constructs a component to display output, containing an image and text.
     *
     * @param label label containing text
     */
    public DialogBox(Label label) {
        label.setWrapText(true);

        this.setAlignment(Pos.TOP_RIGHT);
        this.getChildren().addAll(label);
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

    public static DialogBox getUserDialog(Label label) {
        return new DialogBox(label);
    }

    public static DialogBox getDukeDialog(Label label) {
        var db = new DialogBox(label);
        db.flip();
        return db;
    }
}
