package sus.ui;

import java.util.Objects;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * The UI component that is responsible for displaying outputs to the user.
 */
public class DialogBox extends HBox {

    private static final Image susUser =
            new Image(Objects.requireNonNull(DialogBox.class.getResourceAsStream("/images/SusUser.png")));
    private static final Image susBot =
            new Image(Objects.requireNonNull(DialogBox.class.getResourceAsStream("/images/SusBot.png")));

    /**
     * Constructs a component to display output, containing an image and text.
     *
     * @param label label containing text
     * @param view image to show
     */
    public DialogBox(Label label, ImageView view) {
        label.setWrapText(true);
        label.setMaxWidth(350);

        this.getChildren().addAll(label, view);
        this.setAlignment(Pos.TOP_RIGHT);
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

    public static DialogBox getUserDialog(String message, Color textColour) {
        Label label = new Label(message);
        label.setTextFill(textColour);
        label.setStyle("-fx-font-weight: bold;");
        label.setPadding(new Insets(20, 25, 0, 0));
        return new DialogBox(label, getIcon(susUser));
    }

    public static DialogBox getBotDialog(String message, Color textColor) {
        Label label = new Label(message);
        label.setTextFill(textColor);
        label.setStyle("-fx-font-weight: bold;");
        label.setPadding(new Insets(15, 0, 10, 20));
        var db = new DialogBox(label, getIcon(susBot));
        db.flip();
        return db;
    }

    private static ImageView getIcon(Image image) {
        ImageView icon = new ImageView(image);
        Circle circle = new Circle(30, 30, 30);
        icon.setClip(circle);
        icon.setScaleX(0.8);
        icon.setScaleY(0.8);
        return icon;
    }
}
