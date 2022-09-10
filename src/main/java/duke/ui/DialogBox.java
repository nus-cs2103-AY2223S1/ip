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
        text.setWrapText(true);
        setTextSize(150, 100);
        setDisplayPictureSize(100, 125);
        dialogBoxDesign();
    }

    /**
     * Designs the dialog box.
     */
    private void dialogBoxDesign() {
        this.setPadding(new Insets(20, 10, 10, 10));
        this.setAlignment(Pos.TOP_RIGHT);
        this.getChildren().addAll(text, displayPicture);
        this.setSpacing(10);
        this.setBackground(new Background(
                new BackgroundFill(
                        new Color(0.3, 0.3, 1, 0.3),
                        new CornerRadii(7.5),
                        new Insets(-5, -5, -5, -5))));
    }

    /**
     * Sets the text to specified size.
     *
     * @param width width of text
     * @param height height of text
     */
    private void setTextSize(int width, int height) {
        text.setMinWidth(width);
        text.setMinHeight(height);
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
        this.setBackground(new Background(
                new BackgroundFill(
                        new Color(0, 0.5, 0.0, 0.2),
                        new CornerRadii(7.5),
                        new Insets(-5, -5, -5, -5))));
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