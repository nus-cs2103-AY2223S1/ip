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
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;

/**
 * This control represents a dialogue box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class DialogueBox extends HBox {
    private static final String USER_COLOR = "#00FFFF";
    private static final String DUKE_COLOR = "#FFFF00";

    @FXML
    private Label dialogue;
    @FXML
    private ImageView displayPicture;

    private DialogueBox(String text, Image img, String bgColor) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogueBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialogue.setText(text);
        dialogue.setTextFill(Paint.valueOf(bgColor.toString()));
        displayPicture.setImage(img);
    }

    @FXML
    public void initialize() {
        dialogue.setFont(Font.font("Cascadia Mono", 12));
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

    public static DialogueBox getUserDialogue(String text, Image img) {
        return new DialogueBox(text, img, USER_COLOR);
    }

    public static DialogueBox getDukeDialogue(String text, Image img) {
        var db = new DialogueBox(text, img, DUKE_COLOR);
        db.flip();
        return db;
    }
}
