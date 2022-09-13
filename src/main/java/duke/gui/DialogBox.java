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
    private static final double DB_INSET = 5.0;
    private static final double DB_SPACING = 10.0;


    private static final double DISPLAY_PICTURE_BUBBLE_RADIUS = 45.0;
    private static final double DISPLAY_TEXT_INSET = 12.0;
    private static final double DISPLAY_TEXT_FONT_SIZE = 12.0;
    private static final String DISPLAY_TEXT_FONT = "Courier New Bold";

    /**
     * Creates a new dialog box with a display text and display picture.
     *
     * @param displayText Label containing String of text to be displayed
     * @param displayPicture ImageView containing Image of the display picture
     * @param displayTextBackground Background of the display text
     */
    private DialogBox(Label displayText, Image displayPicture, Background displayTextBackground) {
        displayText.setBackground(displayTextBackground);
        displayText.setPadding(new Insets(DISPLAY_TEXT_INSET));
        displayText.setFont(new Font(DISPLAY_TEXT_FONT, DISPLAY_TEXT_FONT_SIZE));
        displayText.setWrapText(true);

        VBox displayTextBox = new VBox();
        displayTextBox.getChildren().add(displayText);

        Circle displayPictureBubble = new Circle();
        displayPictureBubble.setRadius(DISPLAY_PICTURE_BUBBLE_RADIUS);
        displayPictureBubble.setFill(new ImagePattern(displayPicture));

        this.setPadding(new Insets(DB_INSET));
        this.setSpacing(DB_SPACING);
        this.setAlignment(USER_ALIGNMENT);
        this.getChildren().addAll(displayTextBox, displayPictureBubble);
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
     * @param displayText Label containing the text to be displayed
     * @param displayPicture ImageView containing Image of the display picture
     * @param displayTextBackground Background of the display text
     * @return the dialog box of the user
     */
    public static DialogBox getUserDialog(Label displayText, Image displayPicture, Background displayTextBackground) {
        return new DialogBox(displayText, displayPicture, displayTextBackground);
    }

    /**
     * Factory method for creating a Response dialog box.
     * Display text is placed on the right and display picture is placed on the left.
     *
     * @param displayText Label containing the text to be displayed
     * @param displayPicture ImageView containing Image of the display picture
     * @param displayTextBackground Background of the display text
     * @return the dialog box of the response to the user
     */
    public static DialogBox getChatbotDialog(Label displayText, Image displayPicture,
                                             Background displayTextBackground) {
        DialogBox dialogBox = new DialogBox(displayText, displayPicture, displayTextBackground);
        dialogBox.flip();
        return dialogBox;
    }
}
