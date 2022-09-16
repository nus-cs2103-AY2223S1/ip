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
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class DialogBox extends HBox {
    @FXML
    private Label userDialog;
    @FXML
    private Label dukeDialog;
    @FXML
    private ImageView displayPicture;

    public DialogBox(String userDialog, String dukeDialog, Image displayPicture) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.userDialog.setText(userDialog);
        this.dukeDialog.setText(dukeDialog);
        this.displayPicture.setImage(displayPicture);
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

    public static DialogBox getUserDialog(String dialog, Image displayPicture) {
        return new DialogBox(dialog, "", displayPicture);
    }

    public static DialogBox getDukeDialog(String dialog, Image displayPicture) {
        var db = new DialogBox("", dialog, displayPicture);
        db.flip();
        return db;
    }
}
