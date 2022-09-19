package duke.gui;

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
import javafx.scene.text.Font;

/**
 * Dialog Box GUI for Duke application.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private Circle displayPicture;

    private DialogBox(String text, Image img, boolean isDukeDialog) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);
        displayPicture.setFill(new ImagePattern(img));

        if (isDukeDialog) {
            dialog.setFont(Font.font("Courier New", 12.0));
        } else {
            dialog.setFont(Font.font("Comic Sans MS", 13.0));
        }
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

    public static DialogBox getUserDialog(String text, Image img) {
        DialogBox dialog = new DialogBox(text, img, false);
        dialog.setStyle(dialog.getStyle() + "-fx-background-color: #D3D3D3;");
        return dialog;
    }

    public static DialogBox getDukeDialog(String text, Image img) {
        DialogBox dialog = new DialogBox(text, img, true);
        dialog.setStyle(dialog.getStyle() + "-fx-background-color: #536878;");
        dialog.flip();
        return dialog;
    }
}
