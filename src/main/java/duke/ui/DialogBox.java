package duke.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.io.IOException;
import java.util.Collections;


public class DialogBox extends HBox {

    @FXML
    private ImageView displayPicture;

    @FXML
    private Label dialog;

    public DialogBox(Image displayPicture, String dialog) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.dialog.setText(dialog);
        this.displayPicture.setImage(displayPicture);

        Circle circle = new Circle(15);
        circle.setStroke(Color.WHITE);
        circle.setStrokeWidth(5);
        circle.setCenterX(this.displayPicture.getFitWidth() / 2);
        circle.setCenterY(this.displayPicture.getFitHeight() / 2);
        this.displayPicture.setClip(circle);
    }

    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
    }

    public static DialogBox getUserDialog(Image displayPicture, String dialog) {
        return new DialogBox(displayPicture, dialog);
    }

    public static DialogBox getDukeDialog(Image displayPicture, String dialog) {
        var db = new DialogBox(displayPicture, dialog);
        db.flip();
        return db;
    }
}
