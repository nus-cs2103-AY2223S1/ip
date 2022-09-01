package duke;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
//import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
//import javafx.scene.layout.Background;
//import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
//import javafx.scene.paint.Color;

import java.io.IOException;
import java.util.Collections;

public class DialogBox extends HBox {
    @FXML
    private Label dialog;
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

        dialog.setText(text);
        displayPicture.setImage(img);
    }

    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
    }

    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }

    public static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        return db;
    }

//    private Label text;
//    private ImageView displayPicture;
//
//    public DialogBox(Label l, ImageView iv) {
//        text = l;
//        displayPicture = iv;
//
//        text.setWrapText(true);
//        displayPicture.setFitHeight(100.0);
//        displayPicture.setFitWidth(100.0);
//        this.setSpacing(10.0);
//        this.setPadding(new Insets(15, 0, 15, 0));
//
//        this.setAlignment(Pos.TOP_RIGHT);
//        this.getChildren().addAll(text, displayPicture);
//    }
//
//
//    private void flip() {
//        this.setAlignment(Pos.TOP_LEFT);
//        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
//        FXCollections.reverse(tmp);
//        this.getChildren().setAll(tmp);
//    }
//
//    public static DialogBox getUserDialog(Label l, ImageView iv) {
//        return new DialogBox(l, iv);
//    }
//
//    public static DialogBox getDukeDialog(Label l, ImageView iv) {
//        var db = new DialogBox(l, iv);
//        db.flip();
//        db.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, null, null)));
//        return db;
//    }
}
