package duke.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;

/**
 * DialogBox to display picture and text.
 */
public class DialogBox extends HBox {

    private Label text;
    private ImageView displayPicture;

    /**
     * Constructor for DialogBox.
     *
     * @param l label to be shown
     * @param iv image to be shown
     */
    public DialogBox(Label l, ImageView iv) {
        text = l;
        displayPicture = iv;
        Circle c1 = new Circle(50, 50, 50);
        iv.setClip(c1);

        l.setFont(new Font("Arial", 12));
        text.setBackground(new Background(
                new BackgroundFill(
                        new Color(0.1, 0.1, 0.1, 0.2),
                        new CornerRadii(7.5),
                        new Insets(-5, -5, -5, -5))));
        text.setMinWidth(50);
        text.setMinHeight(25);
        text.setWrapText(true);
        setDisplayPictureSize(100, 125);

        this.setPadding(new Insets(10, 10, 10, 10));
        this.setAlignment(Pos.TOP_RIGHT);
        this.getChildren().addAll(text, displayPicture);
        this.setSpacing(10);
    }

    /**
     * Sets the display picture to specified size.
     *
     * @param width width of picture
     * @param height height of picture
     */
    private void setDisplayPictureSize(int width, int height) {
        displayPicture.setFitWidth(width);
        displayPicture.setFitHeight(height);
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

    public static DialogBox getUserDialog(Label l, ImageView iv) {
        return new DialogBox(l, iv);
    }

    public static DialogBox getDukeDialog(Label l, ImageView iv) {
        DialogBox db = new DialogBox(l, iv);
        db.flip();
        return db;
    }
}