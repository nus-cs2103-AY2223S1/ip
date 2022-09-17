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
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

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
        Circle circle = new Circle(40, 40, 40);
        displayPicture.setImage(img);
        displayPicture.setClip(circle);
    }

    /**
     * Flips the DialogBox object against the y-axis
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
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
        db.changeGreen();
        return db;
    }

    /**
     * Changes the format of the text for
     * error messages.
     */
    public void changeFormat() {
        dialog.setTextFill(Color.web("#FA8072"));
        dialog.setFont(Font.font("System", FontWeight.BOLD, 14));
    }

    /**
     * Changes the colour of the dialogBox
     * to green.
     */
    public void changeGreen() {
        dialog.setStyle("-fx-background-color: #045F5F; -fx-background-radius: 20");
    }
}
