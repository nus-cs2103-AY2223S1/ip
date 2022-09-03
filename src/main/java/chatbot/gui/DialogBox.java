package chatbot.gui;

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
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.io.IOException;

public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;
    private static final float PROFILE_PICTURE_RADIUS = 50;

    public enum Who {
        USER, ZLIMEZ;
    }

    public DialogBox(String l, Image iv, Who who) {
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
        clip.setCenterY(50);
        displayPicture.setClip(clip);
        Color color = who == Who.ZLIMEZ ? Color.LIGHTPINK : Color.LIGHTBLUE;
        this.setBackground(new Background(new BackgroundFill(color, null, null)));
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

    public static DialogBox getUserDialog(String l, Image image) {
        return new DialogBox(l, image, Who.USER);
    }

    public static DialogBox getZlimezDialog(String l, Image image) {
        DialogBox db = new DialogBox(l, image, Who.ZLIMEZ);
        db.flip();
        return db;
    }
}
