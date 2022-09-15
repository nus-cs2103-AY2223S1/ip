package duke;

import java.io.IOException;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;

/** A DialogBox object is used to display a Duke/User message in GUI */
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

        double circleRadius = 50.0;
        double circleX = displayPicture.getX() + circleRadius;
        double circleY = displayPicture.getY() + circleRadius;

        Circle border = new Circle(circleX, circleY, circleRadius);

        dialog.setText(text);
        dialog.setPadding(new Insets(5));
        dialog.setTranslateY(40.0);
        dialog.setStyle("-fx-background-color: #00ffc4;" + "-fx-background-radius: 10;");

        displayPicture.setImage(img);
        displayPicture.setClip(border);
    }

    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);

        dialog.setTranslateX(-10.0);
        dialog.setStyle("-fx-background-color: #ffa500;" + "-fx-background-radius: 10;");
    }

    /**
     * Generates a DialogBox object for User input
     * @param text
     * @param img
     * @return DialogBox object
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }

    /**
     * Generates a DialogBox object for Duke output
     * @param text
     * @param img
     * @return DialogBox object
     */
    public static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        return db;
    }
}
