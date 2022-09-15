package chatbot.gui;

import java.io.IOException;

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
import javafx.scene.shape.Circle;

/**
 * The class acts as the controller of the dialog box component
 * in the GUI.
 */
public class DialogBox extends HBox {
    private static final float PROFILE_PICTURE_RADIUS = 40;
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    /**
     * The enum class limits the validity of party involved in the application
     * to the user and Zlimez the task management bot.
     */
    public enum Who {
        USER, ZLIMEZ;
    }

    /**
     * The constructor for the dialog box.
     *
     * @param l The dialog text.
     * @param iv The profile image of the speaker.
     */
    public DialogBox(String l, Image iv) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(l);
        displayPicture.setImage(iv);

        Circle clip = new Circle(PROFILE_PICTURE_RADIUS);
        clip.setCenterX(PROFILE_PICTURE_RADIUS);
        clip.setCenterY(PROFILE_PICTURE_RADIUS);
        displayPicture.setClip(clip);
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
        setAlignment(Pos.CENTER_LEFT);
    }

    public static DialogBox getUserDialog(String l, Image image) {
        return new DialogBox(l, image);
    }

    public static DialogBox getZlimezDialog(String l, Image image) {
        DialogBox db = new DialogBox(l, image);
        db.flip();
        db.dialog.setStyle("-fx-background-color: #66789f; -fx-text-fill: white; -fx-background-radius: 10;");
        return db;
    }
}
