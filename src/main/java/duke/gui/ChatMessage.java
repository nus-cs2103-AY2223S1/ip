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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

/**
 * A JavaFX component displaying a chat message.
 */
public class ChatMessage extends AnchorPane {
    @FXML
    private Label name;

    @FXML
    private Label message;

    @FXML
    private ImageView profilePicture;

    @FXML
    private HBox header;

    private ChatMessage(String name, String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/ChatMessage.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.name.setText(name);
        message.setText(text);
        profilePicture.setImage(img);
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(header.getChildren());
        Collections.reverse(tmp);
        header.getChildren().setAll(tmp);
        header.setAlignment(Pos.TOP_LEFT);
    }

    public static ChatMessage getUserDialog(String text, Image img) {
        return new ChatMessage("User", text, img);
    }

    public static ChatMessage getDukeDialog(String text, Image img) {
        var db = new ChatMessage("Duke", text, img);
        db.flip();
        return db;
    }
}