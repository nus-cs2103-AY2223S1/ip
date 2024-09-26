package org.Olivia.IO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;


public class DialogBox extends HBox {

    private Label text;
    private ImageView displayPicture;

    public DialogBox(Label l, ImageView iv) {
        this.setSpacing(10);
        text = l;
        this.setMinHeight(100);
        this.text.setMinHeight(40);
        this.text.setMinWidth(50);
        displayPicture = iv;

        text.setWrapText(true);
        displayPicture.setFitWidth(70.0);
        displayPicture.setFitHeight(70.0);

        this.setAlignment(Pos.TOP_RIGHT);
        this.getChildren().addAll(text, displayPicture);
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

    private void changeColor(){
        this.text.setBackground(new Background(new BackgroundFill(Color.color(0.0235294, 0.4078431, 0.88235294), new CornerRadii(10), new Insets(-3,-5,-3,-5))));
    }

    public static DialogBox getUserDialog(Label l, ImageView iv) {
        var db = new DialogBox(l, iv);
        db.changeColor();
        return db;
    }

    public static DialogBox getOliviaDialog(Label l, ImageView iv) {
        var db = new DialogBox(l, iv);
        db.flip();
        return db;
    }
}
