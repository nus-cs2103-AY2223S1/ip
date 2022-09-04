package justin;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * Represents the class that is a horizontal box
 * that contains an ImageView and a Label for the chatbox.
 * @author Justin Cheng.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    /**
     * Constructor for the DialogBox class.
     * @param text The text content in the dialog.
     * @param img The image of the user's and the bot's
     *            avatar.
     * @param title The title of the image.
     */
    private DialogBox(String text, Image img, String title) {
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
        Tooltip.install(displayPicture, new Tooltip(title));
    }

    /**
     * Flips the DialogBox object against the y-axis
     */
    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }

    /**
     * Factory method to initialise the DialogBox of users.
     * @param text The text content in the dialog.
     * @param img The image of the user's avatar.
     * @param title The title of the image.
     * @return A DialogBox for the user.
     */
    public static DialogBox getUserDialog(String text, Image img, String title) {
        DialogBox db = new DialogBox(text, img, title);
        db.changeGray();
        return db;
    }

    /**
     * Factory method to initialise the DialogBox of the bot.
     * @param text The text content in the dialog.
     * @param img The image of the bot;s avatar.
     * @param title The title of the image.
     * @return A DialogBox for the bot.
     */
    public static DialogBox getJustinDialog(String text, Image img, String title) {
        DialogBox db = new DialogBox(text, img, title);
        db.flip();
        db.changeBeige();
        return db;
    }

    public void changeGray() {
        this.setStyle("-fx-background-color: aliceblue");
    }

    public void changeBeige() {
        this.setStyle("-fx-background-color: beige");
    }
}
