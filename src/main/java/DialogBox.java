import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;

import java.awt.*;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;


public class DialogBox extends HBox {

    public Label dialog;
    public ImageView displayPicture;

    public DialogBox(String text, Image img) {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.dialog.setText(text);
        this.dialog.setPadding(new Insets(10));
        this.displayPicture.setImage(img);

        double picX = this.displayPicture.getX();
        double picY = this.displayPicture.getY();
        double height = this.displayPicture.getFitHeight();
        double width = this.displayPicture.getFitWidth();
        Circle clip = new Circle(picX + width/2, picY + height/2, Math.min(height/3, width/3));
        this.displayPicture.setClip(clip);
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

    public static DialogBox getUserDialog(String text, Image img) {
        DialogBox db = new DialogBox(text, img);
        db.setSpacing(10);
        db.setPadding(new Insets(10, 10, 10, 10));
        db.dialog.setStyle("-fx-background-color: GOLDENROD; -fx-background-radius: 20");
        return db;
    }

    public static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        db.setSpacing(10);
        db.setPadding(new Insets(10, 10, 10, 10));
        return db;
    }
}