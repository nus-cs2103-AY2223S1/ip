package dukechatbot.duke;

import java.io.IOException;

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

/**
 * The DialogBox class encapsulates the chat box for the interaction between client and
 * Duke.
 *
 * @author A0233290M
 * @version Week4
 */
public class DialogBox extends HBox {
    /**
     * Label to encapsulate dialog for clients and Duke.
     */
    @FXML
    private Label dialog;
    /**
     * Allows for viewing of respective images used in application.
     */
    @FXML
    private ImageView displayPicture;

    /**
     * Constructor for DialogBox.
     *
     * @param text for the dialog box to display.
     * @param img the image attached to the text.
     */
    private DialogBox(String text, Image img) {
        try {
            FXMLLoader loader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            loader.setController(this);
            loader.setRoot(this);
            loader.load();
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }
        dialog.setText(text);
        displayPicture.setImage(img);
    }

    /**
     * Flips the horizontal arrangements of the children of DialogBox for
     * better differentiation between Duke and client dialogs.
     */
    private void flip() {
        ObservableList<Node> temp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(temp);
        this.getChildren().setAll(temp);
        this.setAlignment(Pos.TOP_LEFT);
    }

    /**
     * Creates a DialogBox instance for users inputs.
     * @param input passed by client.
     * @param img the image attached to the input by client.
     * @return a DialogBox instance for use to display dialogs.
     */
    public static DialogBox getUserDialog(String input, Image img) {
        return new DialogBox(input, img);
    }

    /**
     * Creates a DialogBox instance for Duke responses.
     * @param output responses passed by client.
     * @param img the image attached to the responses by Duke.
     * @return a DialogBox instance for use to display dialogs.
     */
    public static DialogBox getDukeDialog(String output, Image img) {
        DialogBox db = new DialogBox(output, img);
        db.flip();
        return db;
    }
}
