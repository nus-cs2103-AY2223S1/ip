package duke;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class DialogBox extends HBox{
    private Label text;
    private ImageView displayPicture;

    public DialogBox(Label text, ImageView imageView){
        this.text = text;
        this.displayPicture = imageView;

        this.text.setWrapText(true);

        this.displayPicture.setFitWidth(100.0);
        this.displayPicture.setFitHeight(100.0);

        this.setAlignment(Pos.TOP_RIGHT);
        this.getChildren().addAll(text, imageView);

    }

    public static DialogBox getUserDialog(Label text, ImageView userImage) {
        return new DialogBox(text, userImage);
    }

    public static DialogBox getDukeDialog(Label text, ImageView dukeImage) {
        DialogBox dialogBox = new DialogBox(text, dukeImage);
        dialogBox.flip();
        return dialogBox;
    }

    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> dialogBox = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(dialogBox);
        this.getChildren().setAll(dialogBox);
    }


}
