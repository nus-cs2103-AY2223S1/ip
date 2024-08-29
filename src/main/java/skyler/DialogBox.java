package skyler;

import java.io.IOException;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

/**
 * An example of a custom control using FXML.
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class DialogBox extends HBox {

    //@@author jolynloh-reused
    //Reused from https://github.com/RezwanArefin01/ip/blob/master/src/main/java/duke/ui/DialogBox.java
    // with minor modifications
    private static final Background USER_BACKGROUND = new Background(
            new BackgroundFill(Color.web("#43D7E9"), new CornerRadii(20), Insets.EMPTY));
    private static final Background SKYLER_BACKGROUND = new Background(
            new BackgroundFill(Color.web("#E9E9E9"), new CornerRadii(20), Insets.EMPTY));

    @FXML
    private TextFlow textFlow;
    @FXML
    private Text text;
    @FXML
    private Circle displayPicture;

    private DialogBox(String message, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        text.setText(message);
        displayPicture.setFill(new ImagePattern(img));
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
    }

    public static DialogBox getUserDialog(String text, Image img) {
        DialogBox db = new DialogBox(text, img);
        db.textFlow.setBackground(USER_BACKGROUND);
        db.setAlignment(Pos.TOP_RIGHT);
        return db;
    }

    public static DialogBox getSkylerDialog(String text, Image img) {
        DialogBox db = new DialogBox(text, img);
        db.flip();
        db.textFlow.setBackground(SKYLER_BACKGROUND);
        db.setAlignment(Pos.TOP_LEFT);
        return db;
    }
    //@@author
}