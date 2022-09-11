package duke.ui;

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
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

/**
 * Represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class DialogBox extends HBox {
    private static final Background USER_BACKGROUND = new Background(
            new BackgroundFill(Color.web("#797ef6", 0.7), new CornerRadii(15.0), Insets.EMPTY)
    );
    private static final Background DUKE_BACKGROUND = new Background(
            new BackgroundFill(Color.web("#ffffff", 0.15), new CornerRadii(15.0), Insets.EMPTY)
    );
    private static final Background ERROR_BACKGROUND = new Background(
            new BackgroundFill(Color.web("#ff8886", 0.2), new CornerRadii(15.0), Insets.EMPTY)
    );

    @FXML
    private Label dialog;
    @FXML
    private Circle displayPicture;

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
        displayPicture.setFill(new ImagePattern(img));
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
     * Returns a DialogBox for the user with the given text and image.
     * @param text The input entered by the user.
     * @param img The image representing the user.
     * @return The DialogBox for the user.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        DialogBox db = new DialogBox(text, img);
        db.dialog.setBackground(USER_BACKGROUND);
        return db;
    }

    /**
     * Returns a DialogBox for Duke with the given text and image.
     * @param text The response from Duke.
     * @param img The image representing Duke.
     * @param isErrorMessage A boolean indicating if the response is an error message.
     * @return The DialogBox for Duke.
     */
    public static DialogBox getDukeDialog(String text, Image img, Boolean isErrorMessage) {
        DialogBox db = new DialogBox(text, img);
        if (!isErrorMessage) {
            db.dialog.setBackground(DUKE_BACKGROUND);
        } else {
            db.dialog.setBackground(ERROR_BACKGROUND);
        }
        db.flip();
        return db;
    }
}
