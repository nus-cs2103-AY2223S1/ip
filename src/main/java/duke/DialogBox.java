package duke;


import java.io.IOException;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

/**
 * An example of a custom control using FXML.
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class DialogBox extends HBox {

    @FXML
    private StackPane dialogBubble;
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
        configureDialogBubble(text);
        configureDisplayPicture(img);
    }
    private void configureDisplayPicture(Image img) {
        displayPicture.setClip(new Circle(50, 50, 44));
        displayPicture.setImage(img);
        displayPicture.preserveRatioProperty();
    }
    private void configureDialogBubble(String txt) {
        Rectangle box = new Rectangle(300,100);
        box.setArcHeight(20);
        box.setArcWidth(20);
        box.setFill(Color.GHOSTWHITE);

        Label text = new Label(txt);
        text.setFont(new Font("Wingdings", 10));
        text.setPadding(new Insets(10));
        text.setWrapText(true);
        text.setTextAlignment(TextAlignment.JUSTIFY);
        text.setMaxWidth(280);

        box.heightProperty().bind(text.heightProperty());
        box.widthProperty().bind(text.widthProperty());

        dialogBubble.getChildren().addAll(box, text);
        dialogBubble.setMinHeight(Region.USE_PREF_SIZE);
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
        var db = new DialogBox(text, img);
        return db;
    }

    public static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        return db;
    }
}
