package duke.main;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class DialogBox extends HBox {
    private Label text;
    private ImageView displayPicture;

    public DialogBox(Label l, ImageView iv, boolean isRightSide) {
        text = l;
        displayPicture = iv;

        text.setWrapText(true);
        displayPicture.setFitWidth(100.0);
        displayPicture.setFitHeight(100.0);
        if (isRightSide) {
            this.setAlignment(Pos.TOP_RIGHT);
        } else {
            this.setAlignment(Pos.TOP_LEFT);
        }
        this.getChildren().addAll(text, displayPicture);
    }
}
