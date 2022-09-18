package Duke.gui;

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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

public class DialogBox extends AnchorPane {

    @FXML
    private Label name;

    @FXML
    private Label dialog;

    @FXML
    private ImageView profilePicture;

    @FXML
    private HBox boxHeader;

    private DialogBox(String name, String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.name.setText(name);
        dialog.setText(text);
        profilePicture.setImage(img);
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        boxHeader.getChildren().setAll(tmp);
        boxHeader.setAlignment(Pos.TOP_LEFT);
    }

    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox("My dude", text, img);
    }

    public static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox("Drake", text, img);
        db.flip();
        return db;
    }




}

