package duke.gui.controller;

import java.io.IOException;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

/**
 * An example of a custom control using FXML.
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class DialogBox extends HBox {
    /** Text section of the dialog. */
    @FXML
    private Text dialog;

    @FXML
    private HBox dialogContainer;

    @FXML
    private HBox chatContainer;

    /** Display picture of the dialog. */
    @FXML
    private Circle displayPicture;

    /**
     * Returns a DialogBox with text and a display picture.
     *
     * @param text Text of the dialog.
     * @param img Display picture of the dialog.
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
        dialog.setText(text);
        displayPicture.setFill(new ImagePattern(img));
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(chatContainer.getChildren());
        Collections.reverse(tmp);
        chatContainer.getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
    }

    /**
     * Returns a dialog representing the user.
     *
     * @param text Text of the dialog.
     * @param img Display picture of the user.
     * @return DialogBox FXML element representing the user's dialog.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        DialogBox dialogBox = new DialogBox(text, img);
        dialogBox.dialogContainer.setStyle("-fx-background-color: #C8FFCD");
        return dialogBox;
    }

    /**
     * Returns a dialog representing the duke chat-bot.
     *
     * @param text Text of the dialog.
     * @param img Display picture of the duke chat-bot.
     * @return DialogBox FXML element representing the chat-bot's dialog.
     */
    public static DialogBox getDukeDialog(String text, Image img) {
        DialogBox dialogBox = new DialogBox(text, img);
        dialogBox.flip();
        dialogBox.dialogContainer.setStyle("-fx-background-color: #D9E8FB");
        return dialogBox;
    }
}
