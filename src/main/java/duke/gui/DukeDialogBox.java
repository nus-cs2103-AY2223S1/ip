package duke.gui;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 * Duke Dialog Box Class.
 */
public class DukeDialogBox extends HBox {
    @FXML
    private Text dialog;
    @FXML
    private ImageView displayPicture;

    @FXML
    private VBox dialogBox;

    private DukeDialogBox(String text, Image img) {
        try {
            String dukeDialogBoxPath = "/view/DukeDialogBox.fxml";
            FXMLLoader fxmlLoader = new FXMLLoader(
                    this.getClass().getResource(dukeDialogBoxPath));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        dialog.setFont(Font.font("verdana", FontPosture.REGULAR, 14));
        dialog.setText(text);
        dialog.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
        dialogBox.setStyle(
                "-fx-background-color: lightgrey; -fx-text-fill: white; -fx-border-radius: 100px; -fx-padding: 15px;");
        displayPicture.setImage(img);
    }

    /**
     * The getUserDialog function creates a DukeDialogBox object with the given
     * userText
     * and image.
     *
     * @param userText
     *            Display the text that is typed by the user
     * @param image
     *            Display the image of the user's avatar
     * @return A DukeDialogBox object
     */
    public static DukeDialogBox getUserDialog(String userText, Image image) {
        return new DukeDialogBox(userText, image);
    }

    /**
     * The getDukeDialog function creates a DukeDialogBox object with the given text
     * and
     * image.
     * It also flips the DukeDialogBox so that it is facing right instead of left.
     *
     * @param dukeText
     *            Pass the text that will be displayed in the dialog box
     * @param image
     *            Display the image of duke
     * @return A DukeDialogBox object
     */
    public static DukeDialogBox getDukeDialog(String dukeText, Image image) {
        DukeDialogBox db = new DukeDialogBox(dukeText, image);
        db.flip();
        return db;
    }

    /**
     * The flip function flips the HBox around so that it is facing the opposite
     * direction.
     */
    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        dialog.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
        dialog.setFont(Font.font("Comic Sans MS", FontWeight.LIGHT, FontPosture.REGULAR, 14));
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }
}
