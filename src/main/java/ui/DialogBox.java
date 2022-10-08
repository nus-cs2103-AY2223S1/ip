package ui;

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
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * <h1>DialogBox class</h1>
 * A HBox that represents a Dialog containing text and
 * an ImageView.
 */
public class DialogBox extends HBox {
    private static final String UNCLE_CHEONG_DIALOG_STYLE = "-fx-background-color: "
            + "lightblue; -fx-background-radius: 20;";
    private static final String ERROR_DIALOG_STYLE = "-fx-background-color: red; -fx-background-radius: 20;";
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
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }

    /**
     * Factory method to create a DialogBox object that represents the user's dialog.
     *
     * @param l Label to be contained within the DialogBox.
     * @param iv ImageView to be contained within the DialogBox.
     * @return the DialogBox object that represents the user's dialog.
     */
    public static DialogBox getUserDialog(String l, Image iv) {
        DialogBox dialogBox = new DialogBox(l, iv);
        dialogBox.displayPicture.setClip(new Circle(47, 52, 40));
        return dialogBox;
    }

    /**
     * Factory method to create a DialogBox object that represents Uncle Cheong's dialog.
     *
     * @param l Label to be contained within the DialogBox.
     * @param iv ImageView to be contained within the DialogBox.
     * @return the DialogBox object that represents the Uncle Cheong's dialog.
     */
    public static DialogBox getUncleCheongDialog(String l, Image iv) {
        DialogBox dialogBox = new DialogBox(l, iv);
        dialogBox.flip();
        dialogBox.displayPicture.setClip(new Circle(49.5, 49.5, 35));
        dialogBox.dialog.setStyle(UNCLE_CHEONG_DIALOG_STYLE);
        return dialogBox;
    }

    /**
     * Factory method to create a DialogBox object that represents Uncle Cheong's error
     * dialog when the user enters an invalid command.
     *
     * @param l Label to be contained within the DialogBox.
     * @param iv ImageView to be contained within the DialogBox.
     * @return the DialogBox object that represents the Uncle Cheong's dialog.
     */
    public static DialogBox getUncleCheongErrorDialog(String l, Image iv) {
        DialogBox dialogBox = new DialogBox(l, iv);
        dialogBox.flip();
        dialogBox.displayPicture.setClip(new Circle(49.5, 49.5, 40));
        dialogBox.dialog.setStyle(ERROR_DIALOG_STYLE);
        dialogBox.dialog.setTextFill(Color.WHITE);
        return dialogBox;
    }
}
