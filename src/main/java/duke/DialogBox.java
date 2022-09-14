package duke;

import java.io.IOException;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
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
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private Circle circle;

    private boolean isDuke;

    private DialogBox(String text, Image img, boolean isDuke) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);
        if (isDuke) {
            dialog.setStyle("-fx-background-color: #222222; -fx-text-fill: white;"
                    + "-fx-padding: 10px; -fx-background-radius: 10px;");
            if (hasError(text)) {
                dialog.setStyle("-fx-background-color: red; -fx-text-fill: black;"
                        + "-fx-padding: 10px; -fx-background-radius: 10px;");
            }
        } else {
            dialog.setStyle("-fx-background-color: #d9d9d9; -fx-padding: 10px; -fx-background-radius: 10px;");
        }
        circle.setFill(new ImagePattern(img));
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);

        setAlignment(Pos.TOP_LEFT);
    }

    private boolean hasError(String text) {
        String[] temp = text.split("ERROR");
        return temp.length > 1;
    }

    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img, false);
    }

    public static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox(text, img, true);
        db.flip();
        return db;
    }
}
