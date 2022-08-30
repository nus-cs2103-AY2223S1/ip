package duke.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;

public class DialogBox extends HBox {

    private static final double DIALOG_BOX_SPACING = 8.0;
    private static final double DIALOG_BOX_PADDING = 12.0;

    public DialogBox(String response, Image image, double imageSize) {
        Label responseLabel = new Label(response);
        responseLabel.setWrapText(true);

        ImageView displayPicture = new ImageView(image);
        displayPicture.setClip(new Circle(imageSize / 2, imageSize / 2, imageSize / 2));
        displayPicture.setFitHeight(imageSize);
        displayPicture.setFitWidth(imageSize);

        this.setAlignment(Pos.CENTER_RIGHT);
        this.setPadding(new Insets(DIALOG_BOX_PADDING));
        this.setSpacing(DIALOG_BOX_SPACING);
        this.getChildren().addAll(responseLabel, displayPicture);
    }

    private void flip() {
        this.setAlignment(Pos.CENTER_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }

    public static DialogBox getUserDialogBox(String response, Image image, double imageSize) {
        return new DialogBox(response, image, imageSize);
    }

    public static DialogBox getDukeDialogBox(String response, Image image, double imageSize) {
        DialogBox dukeDialogBox = new DialogBox(response, image, imageSize);
        dukeDialogBox.flip();
        return dukeDialogBox;
    }
}
