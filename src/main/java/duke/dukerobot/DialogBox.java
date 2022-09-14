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
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

/**
 * An example of a custom control using FXML.
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
        dialog.getChildren().addAll(labelWithText,displayPicture);
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

    public static DialogBox getUserDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.userAdjust();
        return db;
    }

    public static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        db.dukeAdjust();
        return db;
    }
    public void userAdjust(){
        this.labelWithText.setStyle("-fx-background-color: #c0c0c0;"
                + "-fx-text-fill: black;"
                + "-fx-min-width: 200px;"
                + "-fx-font-size: 14;"
                + "-fx-alignment: baseline-right;"
                + "-fx-border-width: 0;"
                + "-fx-background-radius: 15.0;"
                + "-fx-padding: 15.0");
    }
    public void dukeAdjust(){
        this.labelWithText.setStyle("-fx-background-color: #708090;"
                + "-fx-text-fill: white;"
                + "-fx-min-width: 200px;"
                + "-fx-font-size: 14;"
                + "-fx-alignment: baseline-left;"
                + "-fx-border-width: 0;"
                + "-fx-background-radius: 15.0;"
                + "-fx-padding: 15.0");
    }
    public static DialogBox getExceptionDialog(String response, Image img){
        var db = new DialogBox(response, img);
        db.flip();
        db.exceptionAdjust();
        return db;
    }
    public void exceptionAdjust(){
        this.labelWithText.setStyle("-fx-background-color: #CD5C5C;"
                + "-fx-text-fill: white;"
                + "-fx-min-width: 200px;"
                + "-fx-font-size: 14;"
                + "-fx-alignment: baseline-left;"
                + "-fx-border-width: 0;"
                + "-fx-background-radius: 15.0;"
                + "-fx-padding: 15.0");
    }
}