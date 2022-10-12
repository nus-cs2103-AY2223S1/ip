package duke.gui;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.SVGPath;

/**
 * An example of a custom control using FXML.
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class DialogBox extends HBox {
    @FXML
    private StackPane stackPane;
    @FXML
    private VBox labelContainer;
    @FXML
    private Label dialog;
    @FXML
    private Label time;
    @FXML
    private SVGPath directionIndicator;
    @FXML
    private ImageView displayPicture;
    @FXML
    private Circle circle;

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
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");
        time.setText(dtf.format(LocalDateTime.now()));
        circle.setFill(new ImagePattern(img));
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        labelContainer.setStyle("-fx-background-radius: 0 10 10 10; -fx-padding: 15 20 15 20; "
                + "-fx-background-color: #7FDBFF, #001f3f; -fx-background-insets: 0, 1;");
        directionIndicator.setContent("M20 0 L0 0 L20 20");
        stackPane.setAlignment(Pos.TOP_LEFT);
        labelContainer.setTranslateX(20.5);
        setAlignment(Pos.TOP_LEFT);
    }

    public void setText(String text) {
        dialog.setText(text);
    }

    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }

    public static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        return db;
    }
}
