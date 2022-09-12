package jude;

//@@author cheeheng-reused
//Reused from https://se-education.org/guides/tutorials/javaFxPart4.html with minor modifications.

import java.io.IOException;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

/**
 * An example of a custom control using FXML.
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class DialogBox extends HBox {
    private static final double IMAGE_SIZE_WITH_PADDING = 125.0;
    @FXML
    private Text dialog;
    @FXML
    private ImageView displayPicture;

    /**
     * Creates a dialog box component, which contains a profile picture representing a user, and
     * some text which represents what the user says.
     *
     * @param text What the user is saying.
     * @param img The profile picture of the user.
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

        assert dialog != null : "Dialog text component cannot be null.";
        assert displayPicture != null : "Display picture cannot be null.";
        dialog.setText(text);
        displayPicture.setImage(img);

        //@@author cheeheng-reused
        // Reused from https://stackoverflow.com/questions/38216268/how-to-listen-resize-event-of-stage-in-javafx
        // with some modifications.
        this.widthProperty().addListener((obs, oldVal, newVal) -> {
            // Set text box size accordingly when resize occurs
            dialog.setWrappingWidth(newVal.doubleValue() - IMAGE_SIZE_WITH_PADDING);
        });
        //@@author
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        assert(this.getChildren() != null);
        ObservableList<Node> nodes = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(nodes);
        getChildren().setAll(nodes);
        setAlignment(Pos.TOP_LEFT);
    }

    /**
     * Returns a DialogBox node which corresponds to the user command, i.e. what the user
     * provided as input to the chatbot.
     *
     * @param text The text which represents the user command.
     * @param img The image which represents the user.
     * @return The DialogBox node corresponding to the user command.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }

    /**
     * Returns a DialogBox node which corresponds to the response made by the chatbot.
     *
     * @param text The text which represents the reply made by the chatbot.
     * @param img The image which represents the chatbot.
     * @return The DialogBox node corresponding to the response by the chatbot.
     */
    public static DialogBox getChatbotDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        return db;
    }
}

//@@author
