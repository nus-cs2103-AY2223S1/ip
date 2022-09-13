package duke.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;

/**
 * DialogBox is a HBox that contains a single entry of display text and display picture.
 */
public class DialogBox extends HBox {
    private static final Pos USER_ALIGNMENT = Pos.BOTTOM_RIGHT;
    private static final Pos CHATBOT_ALIGNMENT = Pos.BOTTOM_LEFT;
    private static final double DIALOG_BOX_PADDING = 5.0;
    private static final double DIALOG_BOX_SPACING = 10.0;
    private static final double DISPLAY_TEXT_PADDING = 10.0;
    private static final double DISPLAY_TEXT_FONT_SIZE = 12.0;
    private static final double DISPLAY_PICTURE_BUBBLE_RADIUS = 45.0;
    private Label displayText;
    private Circle displayPictureBubble;
    private VBox displayTextBox;

    /**
     * Creates a new dialog box with a display text and display picture.
     *
     * @param displayText Label containing String of text to be displayed
     * @param displayPicture ImageView containing Image of the display picture
     */
    private DialogBox(Label displayText, Image displayPicture, Background background) {
        this.displayText = displayText;
        this.displayPictureBubble = new Circle();

        this.displayText.setBackground(background);
        this.displayText.setPadding(new Insets(DISPLAY_TEXT_PADDING));
        this.displayText.setFont(new Font(DISPLAY_TEXT_FONT_SIZE));
        this.displayText.setWrapText(true);

        this.displayPictureBubble.setRadius(DISPLAY_PICTURE_BUBBLE_RADIUS);
        this.displayPictureBubble.setFill(new ImagePattern(displayPicture));

        this.displayTextBox = new VBox();
        this.displayTextBox.getChildren().add(displayText);

        this.setPadding(new Insets(DIALOG_BOX_PADDING));
        this.setSpacing(DIALOG_BOX_SPACING);
        this.setAlignment(USER_ALIGNMENT);
        this.getChildren().addAll(this.displayTextBox, this.displayPictureBubble);
    }

    /**
     * Flips the orientation of the dialog box, swapping the position of the
     * display text and display picture. Used for factory methods getResponseDialog.
     */
    private void flip() {
        this.setAlignment(CHATBOT_ALIGNMENT);
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
    public static DialogBox getUserDialog(Label displayText, Image displayPicture, Background background) {
        return new DialogBox(displayText, displayPicture, background);
    }

    /**
     * Factory method for creating a Response dialog box.
     * Display text is placed on the right and display picture is placed on the left.
     *
     * @param displayText Label containing String of text to be displayed
     * @param displayPicture ImageView containing Image of the display picture
     * @return the dialog box of the response to the user
     */
    public static DialogBox getResponseDialog(Label displayText, Image displayPicture, Background background) {
        DialogBox dialogBox = new DialogBox(displayText, displayPicture, background);
        dialogBox.flip();
        return dialogBox;
    }
}
