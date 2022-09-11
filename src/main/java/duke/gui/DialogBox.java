package duke.gui;

import java.io.IOException;
import java.util.Collections;

import duke.Response;
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

/**
 * An example of a custom control using FXML.
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    private final Circle c = new Circle(25, 25, 25);

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
        displayPicture.setClip(c);
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
     * Gets user input and image to be printed GUI.
     * @param text string of user input.
     * @param img image of user.
     * @return DialogBox of user.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }

    /**
     * Gets chatbot response and image to be printed GUI flipped.
     * @param response string of chatbot response
     * @param img image of chatbot
     * @return DialogBox of chatbot.
     */
    public static DialogBox getDukeDialog(Response response, Image img) {
        var db = new DialogBox(response.getText(), img);
        db.flip();

        if (response.isErrorResponse()) {
            //Reused from https://stackoverflow.com/questions/14370554/how-to-programmatically-set-the-color-or-texture-of-a-tab-label-in-javafx
            // with minor modifications
            db.dialog.setStyle(db.dialog.getStyle() + "-fx-background-color: #e94b3c;");
        } else {
            db.dialog.setStyle(db.dialog.getStyle() + "-fx-background-color: #57504d;");
        }
        return db;
    }
}
