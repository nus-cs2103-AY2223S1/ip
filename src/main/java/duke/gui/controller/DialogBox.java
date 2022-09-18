package duke.gui.controller;

import java.io.IOException;
import java.util.Collections;

import duke.util.Response;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

/**
 * An example of a custom control using FXML.
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class DialogBox extends HBox {

    private static final String ERROR_BACKGROUND_COLOR = "#9F0000";
    private static final String SUCCESS_BACKGROUND_COLOR = "#0B82C5";
    private static final String USER_BACKGROUND_COLOR = "white";

    /** Text section of the dialog. */
    @FXML
    private Text dialog;
    /** Display picture of the dialog. */
    @FXML
    private Circle displayPicture;
    @FXML
    private HBox dialogContainer;
    @FXML
    private HBox chatContainer;



    /**
     * Returns a DialogBox with text and a display picture.
     *
     * @param text
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
        dialogBox.setBackgroundColor(USER_BACKGROUND_COLOR);
        return dialogBox;
    }

    /**
     * Returns a dialog representing the duke chat-bot.
     *
     * @param response Text of the dialog.
     * @param img Display picture of the duke chat-bot.
     * @return DialogBox FXML element representing the chat-bot's dialog.
     */
    public static DialogBox getDukeDialog(Response response, Image img) {
        boolean isError = response.isError();
        DialogBox dialogBox = new DialogBox(response.getMessage(), img);
        dialogBox.flip();
        dialogBox.dialog.setFill(Color.WHITE);
        if (isError) {
            dialogBox.setBackgroundColor(ERROR_BACKGROUND_COLOR);
        } else {
            dialogBox.setBackgroundColor(SUCCESS_BACKGROUND_COLOR);
        }
        return dialogBox;
    }

    /**
     * Sets the background color of the dialog box.
     *
     * @param color Background color of the dialog box.
     */
    private void setBackgroundColor(String color) {
        this.dialogContainer.setStyle("-fx-background-color: " + color);
    }
}
