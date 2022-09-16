package duke;

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
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;



public class DialogBox extends HBox {
    private static final String[] dukeStyleClasses = {"duke-dialog-background"};
    private static final String[] userStyleClasses = {"user-dialog-background"};

    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    private DialogBox(String text, Image img, String[] styleClasses) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Circle clip = new Circle();
        clip.setCenterX(50.0f);
        clip.setCenterY(50.0f);
        clip.setRadius(50.0f);
        displayPicture.setClip(clip);
        dialog.setText(text);
        dialog.getStyleClass().addAll(styleClasses);
        displayPicture.setImage(img);
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
        DialogBox user = new DialogBox(text, img, userStyleClasses);
        return user;
    }

    public static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox(text, img, dukeStyleClasses);
        db.flip();
        return db;
    }
}


