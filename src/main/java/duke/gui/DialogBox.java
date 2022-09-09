package duke.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;

/**
 * DialogBox is a HBox that contains a single entry of display text and display picture.
 */
public class DialogBox extends HBox {
    private static final double DISPLAY_TEXT_PADDING = 10.0;
    private static final double DISPLAY_TEXT_FONT_SIZE = 12.0;
    private Label displayText;
    private ImageView displayPicture;

    /**
     * Creates a new dialog box with a display text and display picture.
     *
     * @param displayText Label containing String of text to be displayed
     * @param displayPicture ImageView containing Image of the display picture
     */
    private DialogBox(Label displayText, ImageView displayPicture) {
        this.displayText = displayText;
        this.displayPicture = displayPicture;

        this.displayText.setPadding(new Insets(DISPLAY_TEXT_PADDING));
        this.displayText.setFont(new Font(DISPLAY_TEXT_FONT_SIZE));
        this.displayText.setWrapText(true);

        this.displayPicture.setFitWidth(100.0);
        this.displayPicture.setFitHeight(100.0);

        this.setAlignment(Pos.TOP_RIGHT);
        this.getChildren().addAll(displayText, displayPicture);
    }

    /**
     * Flips the orientation of the dialog box, swapping the position of the
     * display text and display picture. Used for factory methods getResponseDialog.
     */
    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> nodeList = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(nodeList);
        this.getChildren().setAll(nodeList);
    }

    /**
     * Factory method for creating a User dialog box.
     * Display text is placed on the left and display picture is placed on the right.
     *
     * @param displayText Label containing String of text to be displayed
     * @param displayPicture ImageView containing Image of the display picture
     * @return the dialog box of the user
     */
    public static DialogBox getUserDialog(Label displayText, ImageView displayPicture) {
        return new DialogBox(displayText, displayPicture);
    }

    /**
     * Factory method for creating a Response dialog box.
     * Display text is placed on the right and display picture is placed on the left.
     *
     * @param displayText Label containing String of text to be displayed
     * @param displayPicture ImageView containing Image of the display picture
     * @return the dialog box of the response to the user
     */
    public static DialogBox getResponseDialog(Label displayText, ImageView displayPicture) {
        DialogBox dialogBox = new DialogBox(displayText, displayPicture);
        dialogBox.flip();
        return dialogBox;
    }
}
