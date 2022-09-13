package duke.gui;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.util.Collections;
import java.util.List;

/**
 * An example of a custom control using FXML.
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a TextArea
 * containing text from the speaker.
 */
public class DialogBox extends HBox {
    @FXML
    private TextArea dialog;
    @FXML
    private ImageView displayPicture;

    /**
     * This method is DialogBox constructor. A DialogBox object consists of dialog attribute (TextArea)
     * and displayPicture attribute (ImageView).
     * @param text text that dialog contains.
     * @param img image for ImageView.
     * @param color color of the dialog box.
     */
    private DialogBox(String text, javafx.scene.image.Image img, String color) {
        try {
            javafx.fxml.FXMLLoader fxmlLoader = new javafx.fxml.FXMLLoader(duke.gui.MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
        dialog.setText(text);
        dialog.setWrapText(true);
        dialogFitText();
        dialog.setStyle(String.format("-fx-line-spacing: 8px; -fx-control-inner-background: %s; -fx-hbar-policy:never; "
                + "-fx-vbar-policy:never; -fx-padding: 2;", color));
        displayPicture.setImage(img);
        displayPicture.setClip(new javafx.scene.shape.Circle(25, 25, 25));
        this.setSpacing(6);
    }

    /**
     * Formats TextArea GUI to fit text.
     * Modified from https://stackoverflow.com/a/25646466
     */
    private void dialogFitText() {
        SimpleIntegerProperty count = new SimpleIntegerProperty(20);
        int rowHeight = 10;
        dialog.prefHeightProperty().bindBidirectional(count);
        dialog.minHeightProperty().bindBidirectional(count);
        dialog.scrollTopProperty().addListener(new ChangeListener<Number>(){
            @Override
            public void changed(ObservableValue<? extends Number> ov, Number oldVal, Number newVal) {
                if(newVal.intValue() > rowHeight){
                    count.setValue(count.get() + newVal.intValue());
                }
            }
        });
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

    /**
     * Creates the DialogBox that contains user input command.
     * @return DialogBox.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img, "LAVENDER");
    }

    /**
     * Creates a list of dialog boxes that contain chatbot response messages.
     * @return DialogBox.
     */
    public static List<DialogBox> getDukeDialogs(List<String> uiTexts, Image img) {
        List<DialogBox> dialogs = new java.util.ArrayList<>();
        for (String uiText : uiTexts) {
            var db = new DialogBox(uiText, img, "LIGHTGOLDENRODYELLOW");
            db.flip();
            dialogs.add(db);
        }
        return dialogs;
    }
}
