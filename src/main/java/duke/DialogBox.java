package duke;

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

    public DialogBox(Label l, ImageView iv) {
        text = l;
        displayPicture = iv;

        displayPicture.setFitWidth(60.0);
        displayPicture.setFitHeight(60.0);

        //Use a rectangle to clip the Images
        Rectangle clip = new Rectangle(
                displayPicture.getFitWidth(),
                displayPicture.getFitHeight()
        );
        clip.setArcWidth(30);
        clip.setArcHeight(30);
        displayPicture.setClip(clip);


        text.setWrapText(true);
        //So text doesn't overflow into User/ Duke icon area
        text.setMaxWidth(225);
        // Padding within label
        text.setPadding(new Insets(10,7,10,7));
        //Modify font
        text.setFont(Font.font("Lucida Sans Unicode", 10));


        this.setAlignment(Pos.TOP_RIGHT);
        this.getChildren().addAll(text, displayPicture);

        //Space between image and text
        this.setSpacing(7);
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
                            new CornerRadii(12,12,12,12,false),
                            new Insets(0))
                        )
        );
        return new DialogBox(l, iv);
    }

    public static DialogBox getDukeDialog(Label l, ImageView iv) {
        //Background colour for the text
        l.setBackground(new Background(
                            new BackgroundFill(Color.color(0.996, 0.5882, 0.902),
                            new CornerRadii(12,12,12,12,false),
                            new Insets(0))
                        )
        );
        var db = new DialogBox(l, iv);
        db.flip();
        return db;
    }
}