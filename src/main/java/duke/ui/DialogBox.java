package duke.ui;

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
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * A class that composed ImageView and Label control.
 */
public class DialogBox extends HBox {

    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    //ImageView.setClip solution reference from https://www.programcreek.com/java-api-examples/?api=javafx.scene.shape.Circle
    private DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        dialog.setText(text);
        displayPicture.setImage(img);
        Circle clip = new Circle(displayPicture.getFitWidth() / 2.3);
        clip.setCenterX(displayPicture.getFitWidth() / 2);
        clip.setCenterY(displayPicture.getFitHeight() / 2);
        displayPicture.setClip(clip);
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
        var ub = new DialogBox(text, img);
        ub.changeUserBackgroundColor();
        return ub;
    }

    public static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        db.changeDukeBackgroundColor();
        return db;
    }

    //Method below reference from https://www.tabnine.com/code/java/methods/javafx.scene.layout.HBox/setBackground
    private void changeDukeBackgroundColor() {
        Background dukeBackground = new Background(new BackgroundFill(Color.LAVENDER, null, null));
        super.setBackground(dukeBackground);
    }

    //Method below reference from https://www.tabnine.com/code/java/methods/javafx.scene.layout.HBox/setBackground
    private void changeUserBackgroundColor() {
        Background userBackground = new Background(new BackgroundFill(Color.LIGHTCYAN, null, null));
        super.setBackground(userBackground);
    }
}
