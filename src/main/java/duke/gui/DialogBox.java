package duke.gui;

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
 * DialogBox used to wrap the text in the application.
 * @author Jason
 */
public class DialogBox extends HBox {
    private static final Background BG_USER = new Background(
            new BackgroundFill(Color.valueOf("#192734"), new CornerRadii(20), Insets.EMPTY));
    private static final Background BG_DUKE = new Background(
            new BackgroundFill(Color.valueOf("#22303C"), new CornerRadii(20), Insets.EMPTY));
    @FXML
    private TextFlow dialog;
    @FXML
    private Text text;
    @FXML
    private Circle displayPicture;

    /**
     * Constructs a DialogBox to contain the texts conversations.
     * @param text Label containing the texts of the conversation.
     * @param img Image of the user that sent that text.
     */
    private DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.text.setText(text);
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
     * Creates a User Dialog box.
     * @param text Text to be placed in dialog box.
     * @param img User Profile picture.
     * @return Dialog box object containing user's input.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        DialogBox db = new DialogBox(text, img);
        db.dialog.setBackground(BG_USER);
        return db;
    }

    /**
     * Creates a Duke Dialog box.
     * @param text Text to be placed in dialog box.
     * @param img Duke Profile picture.
     * @return Dialog box object containing duke's response.
     */
    public static DialogBox getDukeDialog(String text, Image img) {
        DialogBox db = new DialogBox(text, img);
        db.flip();
        db.dialog.setBackground(BG_DUKE);
        return db;
    }
}
