package duke.gui;

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
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

/**
 * Represents a dialog box in the GUI
 */
public class DialogBox extends HBox {
    private static Background dukeBg = new Background(new BackgroundFill(Paint.valueOf("B0B0B0"),
            CornerRadii.EMPTY, Insets.EMPTY));
    private static Background userBg = new Background(new BackgroundFill(Paint.valueOf("CDCDCD"),
            CornerRadii.EMPTY, Insets.EMPTY));

    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;
    @FXML
    private Circle clip = new Circle(46, 50, 45);




    private DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
            this.setSpacing(50);
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);
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

    /**
     * Returns a DialogBox for the user
     */
    public static DialogBox getUserDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.displayPicture.setClip(db.clip);
        db.setBackground(userBg);
        return db;
    }

    /**
     * Returns a DialogBox for Duke
     */
    public static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.displayPicture.setClip(db.clip);
        db.flip();
        db.setBackground(dukeBg);
        return db;
    }
}
