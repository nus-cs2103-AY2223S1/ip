package sakura;

import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

/**
 * An example of a custom control using FXML.
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class DialogBox extends HBox {
    @FXML
    private Text dialog;
    @FXML
    private ImageView displayPicture;

    private DialogBox(String s, Image img, TextAlignment t) {
        Circle clip = new Circle(50, 50, 45);
        dialog = new Text(s);
        dialog.setFont(new Font("SF Mono Medium", 11));
        dialog.setFill(Paint.valueOf("FFFFFF"));
        ImageView iv = new ImageView(img);
        iv.setClip(clip);
        displayPicture = iv;

        dialog.setTextAlignment(t);
        dialog.setWrappingWidth(330);
        displayPicture.setFitWidth(100.0);
        displayPicture.setFitHeight(100.0);

        if (t == TextAlignment.LEFT) {
            this.setBackground(new Background(new BackgroundFill(Color.valueOf("cd5c78"), new CornerRadii(5), new Insets(1))));
        } else {
            this.setBackground(new Background(new BackgroundFill(Color.valueOf("5cb1cd"), new CornerRadii(5), new Insets(1))));
        }
        this.setAlignment(Pos.CENTER_RIGHT);
        this.getChildren().addAll(dialog, displayPicture);
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.CENTER_LEFT);
    }

    public static DialogBox getUserDialog(String s, Image img) {
        return new DialogBox("\n" + s + "\n", img, TextAlignment.RIGHT);
    }

    public static DialogBox getDukeDialog(String s, Image img) {
        var db = new DialogBox("\n" + s, img, TextAlignment.LEFT);
        db.flip();
        return db;
    }
}
