package duke.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class DialogBox extends HBox {
    private Label displayText;
    private ImageView displayPicture;

    private DialogBox(Label displayText, ImageView displayPicture) {
        this.displayText = displayText;
        this.displayPicture = displayPicture;

        displayText.setWrapText(true);
        
        displayPicture.setFitWidth(100.0);
        displayPicture.setFitHeight(100.0);

        this.setAlignment(Pos.TOP_RIGHT);
        this.getChildren().addAll(displayText,  displayPicture);
    }

    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> nodeList = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(nodeList);
        this.getChildren().setAll(nodeList);
    }

    public static DialogBox getUserDialog(Label displayText, ImageView displayPicture) {
        return new DialogBox(displayText, displayPicture);
    }

    public static DialogBox getResponseDialog(Label displayText, ImageView displayPicture) {
        DialogBox dialogBox = new DialogBox(displayText, displayPicture);
        dialogBox.flip();
        return dialogBox;
    }
}
