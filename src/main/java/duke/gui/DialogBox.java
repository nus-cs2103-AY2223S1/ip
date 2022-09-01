package duke.gui;

import java.io.IOException;
import java.util.Collections;

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
 * An example of a custom control using FXML.
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

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

    public static DialogBox getUserDialog(String text, Image img) {
        var dialogBox = new DialogBox(text, img);
        dialogBox.addUserTextDesign();
        return dialogBox;
    }

    public static DialogBox getDukeDialog(String text, Image img) {
        var dialogBox = new DialogBox(text, img);
        dialogBox.flip();
        dialogBox.addBotTextDesign();
        return dialogBox;
    }

    /**
     * Add a text bubble design for user text.
     */
    public void addUserTextDesign() {
        this.dialog.setStyle("-fx-background-color: #0096FF;"
                + "-fx-text-fill: white;"
                + "-fx-min-width: 200px;"
                + "-fx-wrap-text: true;"
                + "-fx-font-size: 14;"
                + "-fx-alignment: baseline-left;"
                + "-fx-border-width: 0;"
                + "-fx-background-radius: 15.0;"
                + "-fx-padding: 15.0");
    }

    /**
     * Add a text bubble design for bot text.
     */
    public void addBotTextDesign() {
        this.dialog.setStyle("-fx-background-color: #DCDCDC;"
                + "-fx-text-fill: black;"
                + "-fx-min-width: 200px;"
                + "-fx-wrap-text: true;"
                + "-fx-font-size: 14;"
                + "-fx-alignment: baseline-left;"
                + "-fx-border-width: 0;"
                + "-fx-background-radius: 15.0;"
                + "-fx-padding: 15.0");
    }
}
