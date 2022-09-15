package duke.dukerobot;

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
import javafx.scene.layout.HBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;


/**
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class DialogBox extends HBox {
    @FXML
    private HBox dialog;
    @FXML
    private Label labelWithText;
    @FXML
    private Circle displayPicture;

    private DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        labelWithText.setText(text);
        displayPicture.setFill(new ImagePattern(img));
        dialog.getChildren().addAll(labelWithText, displayPicture);
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.dialog.getChildren());
        Collections.reverse(tmp);
        this.dialog.getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
    }
    /**
     * Gets dialog box for user.
     * @param text The user input.
     * @param img User's profile picture.
     * @return A dialogbox containing user's input.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.userAdjust();
        return db;
    }
    /**
     * Gets dialog box for duke.
     * @param text The response from duke.
     * @param img Duke's profile picture.
     * @return A dialogbox containing duke's response.
     */
    public static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        db.dukeAdjust();
        return db;
    }
    /**
     * Adjusts user's dialogbox to a certain pattern.
     */
    public void userAdjust() {
        this.labelWithText.setStyle("-fx-background-color: #c0c0c0;"
                + "-fx-text-fill: black;"
                + "-fx-min-width: 200px;"
                + "-fx-font-size: 18;"
                + "-fx-alignment: baseline-right;"
                + "-fx-border-width: 0;"
                + "-fx-background-radius: 15.0;"
                + "-fx-padding: 15.0");
    }
    /**
     * Adjusts Duke's dialogbox to a certain pattern.
     */
    public void dukeAdjust() {
        this.labelWithText.setStyle("-fx-background-color: #708090;"
                + "-fx-text-fill: white;"
                + "-fx-min-width: 200px;"
                + "-fx-font-size: 14;"
                + "-fx-alignment: baseline-left;"
                + "-fx-border-width: 0;"
                + "-fx-background-radius: 15.0;"
                + "-fx-padding: 15.0");
    }
    /**
     * Gets dialog box for duke's exception message.
     * @param response The response from duke.
     * @param img Duke's profile picture.
     * @return A dialogbox containing duke's response.
     */
    public static DialogBox getExceptionDialog(String response, Image img) {
        var db = new DialogBox(response, img);
        db.flip();
        db.exceptionAdjust();
        return db;
    }
    /**
     * Wraps duke's response to a special pattern if exception is caught.
     */
    public void exceptionAdjust() {
        this.labelWithText.setStyle("-fx-background-color: #CD5C5C;"
                + "-fx-text-fill: white;"
                + "-fx-min-width: 200px;"
                + "-fx-font-size: 18;"
                + "-fx-alignment: baseline-left;"
                + "-fx-border-width: 0;"
                + "-fx-background-radius: 15.0;"
                + "-fx-padding: 15.0");
    }
}