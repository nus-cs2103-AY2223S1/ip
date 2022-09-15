package duke.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.geometry.Insets;
import javafx.geometry.Pos;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.layout.Background;
import javafx.scene.text.Font;

public class DialogBox extends HBox {

    private Label text;
    private ImageView displayPicture;
    private static int IMAGE_FIT_WIDTH_HEIGHT = 60;
    private static int TEXT_MAX_WIDTH = 225;
    private static int TEXT_PADDING_TOP_BOTTOM = 10;
    private static int TEXT_PADDING_LEFT_RIGHT = 7;
    private static String FONT_STYLE = "Lucida Sans Unicode";
    private static int FONT_SIZE = 10;
    private static int CLIP_ARC_WIDTH_HEIGHT = 30;
    private static int DIALOG_BOX_CORNER_RADII = 12;

    public DialogBox(Label l, ImageView iv) {
        text = l;
        displayPicture = iv;

        displayPicture.setFitWidth(IMAGE_FIT_WIDTH_HEIGHT);
        displayPicture.setFitHeight(IMAGE_FIT_WIDTH_HEIGHT);

        //Use a rectangle to clip the Images
        Rectangle clip = clipForDisplayPicture(displayPicture);
        displayPicture.setClip(clip);


        text.setWrapText(true);
        //So text doesn't overflow into User/ Duke icon area
        text.setMaxWidth(TEXT_MAX_WIDTH);
        // Padding within label/ text
        text.setPadding(new Insets(TEXT_PADDING_TOP_BOTTOM,TEXT_PADDING_LEFT_RIGHT,
                TEXT_PADDING_TOP_BOTTOM,TEXT_PADDING_LEFT_RIGHT));
        //Modify font
        text.setFont(Font.font(FONT_STYLE, FONT_SIZE));


        this.setAlignment(Pos.TOP_RIGHT);
        this.getChildren().addAll(text, displayPicture);

        //Space between image and text
        this.setSpacing(7);
    }

    private Rectangle clipForDisplayPicture(ImageView displayPicture) {
        Rectangle clip = new Rectangle(
                displayPicture.getFitWidth(),
                displayPicture.getFitHeight()
        );
        clip.setArcWidth(CLIP_ARC_WIDTH_HEIGHT);
        clip.setArcHeight(CLIP_ARC_WIDTH_HEIGHT);
        return clip;
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

    public static DialogBox getUserDialog(Label l, ImageView iv) {
        //Colour of the text
        l.setTextFill(Color.WHITESMOKE);
        //Background colour for the text
        l.setBackground(new Background(
                            new BackgroundFill(Color.color(0.3254, 0.3529, 0.996),
                            new CornerRadii(DIALOG_BOX_CORNER_RADII,DIALOG_BOX_CORNER_RADII,DIALOG_BOX_CORNER_RADII,DIALOG_BOX_CORNER_RADII,false),
                            new Insets(0))
                        )
        );
        return new DialogBox(l, iv);
    }

    public static DialogBox getDukeDialog(Label l, ImageView iv) {
        //Background colour for the text
        l.setBackground(new Background(
                            new BackgroundFill(Color.color(0.996, 0.5882, 0.902),
                            new CornerRadii(DIALOG_BOX_CORNER_RADII,DIALOG_BOX_CORNER_RADII,DIALOG_BOX_CORNER_RADII,DIALOG_BOX_CORNER_RADII,false),
                            new Insets(0))
                        )
        );
        var db = new DialogBox(l, iv);
        db.flip();
        db.setBackground(new Background(
                        new BackgroundFill(Color.BEIGE,
                                new CornerRadii(DIALOG_BOX_CORNER_RADII,DIALOG_BOX_CORNER_RADII,DIALOG_BOX_CORNER_RADII,DIALOG_BOX_CORNER_RADII,false),
                                new Insets(0))
                )
        );
        return db;
    }
}