package arc;

import java.io.IOException;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

/**
 * An example of a custom control using FXML.
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class DialogBox extends HBox {
    @FXML
    private StackPane dialog;
    @FXML
    private ImageView displayPicture;
    private Circle clip = new Circle();
    private final double CLIP_LENGTH = 50.0;

    private DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        configureDisplayPicture(img);
        configureDialogBox(text);
    }

    private void configureDisplayPicture(Image img) {
        displayPicture.setImage(img);
        displayPicture.setFitHeight(CLIP_LENGTH);
        displayPicture.setFitWidth(CLIP_LENGTH);

        clip.setCenterX(CLIP_LENGTH / 2);
        clip.setCenterY(CLIP_LENGTH / 2);
        clip.setRadius(CLIP_LENGTH / 2);

        displayPicture.setClip(clip);
    }

    private void configureDialogBox(String txt) {
        Rectangle box = new Rectangle(300, 100);
        box.setArcHeight(20);
        box.setArcWidth(20);
        box.setFill(Color.WHITESMOKE);

        Label text = new Label(txt);
        text.setFont(new Font(10));
        text.setPadding(new Insets(10));

        box.heightProperty().bind(text.heightProperty());
        box.widthProperty().bind(text.widthProperty());

        dialog.getChildren().addAll(box, text);
        dialog.setMinHeight(Region.USE_PREF_SIZE);
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
        var db = new DialogBox(text, img);
        db.setPadding(new Insets(10));
        return db;
    }

    public static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.setPadding(new Insets(10));
        db.flip();
        return db;
    }
}
