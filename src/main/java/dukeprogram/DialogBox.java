package dukeprogram;

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
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * An example of a custom control using FXML.
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class DialogBox extends HBox {

    @FXML
    private Label name;
    @FXML
    private Label dialog;

    @FXML
    private VBox dialogLayout;

    @FXML
    private ImageView displayPicture;

    private DialogBox(String dialogText, String nameText, Image img, Color color) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        name.setText(nameText);
        dialog.setText(dialogText);

        displayPicture.setImage(img);
        double width = displayPicture.getFitWidth() / 2;
        double height = displayPicture.getFitHeight() / 2;
        Circle circle = new Circle(
                width,
                height,
                Math.min(width, height) - 5);

        DropShadow dropShadow = new DropShadow();
        dropShadow.setOffsetX(0.5);
        dropShadow.setOffsetY(0.5);

        circle.setEffect(dropShadow);
        displayPicture.setClip(circle);

        dialog.setPadding(new Insets(5, 5, 5, 5));

        this.setBackground(new Background(new BackgroundFill(
                color,
                new CornerRadii(10),
                new Insets(5, 5, 5, 5)))
        );
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
        dialogLayout.setAlignment(Pos.TOP_LEFT);
    }

    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, Duke.getUser().getName(), img, Color.TURQUOISE);
    }

    public static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox(text, "Duke", img, Color.BURLYWOOD);
        db.flip();
        return db;
    }
}
