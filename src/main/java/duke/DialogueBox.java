package duke;

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
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;
import javafx.geometry.Insets;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

/**
 * An example of a custom control using FXML.
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class DialogueBox extends HBox {

    private static final double RADIUS_RATIO = 2.2;
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    private DialogueBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        double circleRadius = displayPicture.getFitWidth() / RADIUS_RATIO;
        double circleX = displayPicture.getX() + circleRadius;
        double circleY = displayPicture.getY() + circleRadius;

        Circle circle = new Circle(circleX, circleY, circleRadius);

        dialog.setText(text);
        displayPicture.setImage(img);
        displayPicture.setClip(circle);

        dialog.setStyle("-fx-background-color: #00ffc4;" + "-fx-background-radius: 10;");
        dialog.setPadding(new Insets(8));
        dialog.setFont(Font.font("Courier New", FontWeight.SEMI_BOLD, FontPosture.REGULAR, 13));

    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
        double circleRadius = displayPicture.getFitWidth() / RADIUS_RATIO;
        double circleX = displayPicture.getX() + circleRadius;
        double circleY = displayPicture.getY() + circleRadius;

        Circle circle = new Circle(circleX, circleY, circleRadius);

        displayPicture.setClip(circle);

        dialog.setStyle("-fx-background-color: #00ffc4;" + "-fx-background-radius: 10;");
        dialog.setPadding(new Insets(8));
        dialog.setFont(Font.font("Comic Sans MS", FontWeight.SEMI_BOLD, FontPosture.REGULAR, 13));
    }

    public static DialogueBox getUserDialog(String text, Image img) {
        return new DialogueBox(text, img);
    }

    /**
     * Duke's dialogue is exactly the same as user's dialogue, just flipped
     * @param text
     * @param img
     * @return
     */
    public static DialogueBox getDukeDialog(String text, Image img) {
        var db = new DialogueBox(text, img);
        db.flip();
        return db;
    }
}
