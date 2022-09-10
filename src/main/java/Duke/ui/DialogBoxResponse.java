package Duke.ui;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class DialogBoxResponse extends HBox {

    public DialogBoxResponse(Label l, ImageView iv) {

        l.setWrapText(true);
        iv.setFitWidth(100.0);
        iv.setFitHeight(100.0);

        this.setAlignment(Pos.TOP_LEFT);
        this.getChildren().addAll(iv, l);
    }
}
