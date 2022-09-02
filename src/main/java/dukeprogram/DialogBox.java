package dukeprogram;

import java.io.IOException;
import java.util.Collections;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.ParallelTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

/**
 * An example of a custom control using FXML.
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class DialogBox extends HBox {

    @FXML
    private HBox parentPanel;

    @FXML
    private Label name;
    @FXML
    private Label dialog;

    @FXML
    private VBox dialogLayout;

    @FXML
    private ImageView displayPicture;

    private DialogBox(String dialogText, String nameText, Image img, Color color) {
        this(dialogText, nameText, img, color, false);
    }

    private DialogBox(String dialogText, String nameText, Image img, Color color, boolean isFlipped) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        name.setText(nameText);
        dialog.setText(dialogText);

        setDisplayPicture(img);

        dialog.setPadding(new Insets(5, 5, 5, 5));

        this.setBackground(new Background(new BackgroundFill(
                color,
                new CornerRadii(10),
                new Insets(5, 5, 5, 5)))
        );

        if (isFlipped) {
            this.flip();
        }

        this.layoutBoundsProperty().addListener(new ChangeListener<Bounds>() {
            @Override
            public void changed(ObservableValue<? extends Bounds> observable, Bounds oldValue, Bounds newValue) {
                TranslateTransition translateTransition = new TranslateTransition(Duration.millis(500),
                        DialogBox.this);
                translateTransition.setFromX((isFlipped ? -1 : 1) * newValue.getWidth() / 2);
                translateTransition.setFromY(-newValue.getHeight() / 2);
                translateTransition.setToX(0);
                translateTransition.setToY(0);

                ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(500),
                        DialogBox.this);
                scaleTransition.setFromX(0);
                scaleTransition.setFromY(0);
                scaleTransition.setToX(1);
                scaleTransition.setToY(1);

                new ParallelTransition(translateTransition, scaleTransition).play();
                DialogBox.this.layoutBoundsProperty().removeListener(this);
            }
        });
    }

    private void setDisplayPicture(Image img) {
        displayPicture.setImage(img);
        double width = displayPicture.getFitWidth() / 2;
        double height = displayPicture.getFitHeight() / 2;
        Circle circle = new Circle(
                width,
                height,
                Math.min(width, height) - 5);

        DropShadow dropShadow = new DropShadow();
        dropShadow.setOffsetX(0.5);
        dropShadow.setOffsetY(0.5);

        circle.setEffect(dropShadow);
        displayPicture.setClip(circle);
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(parentPanel.getChildren());
        Collections.reverse(tmp);
        parentPanel.getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
        parentPanel.setAlignment(Pos.TOP_LEFT);
        dialogLayout.setAlignment(Pos.TOP_LEFT);
    }

    private Timeline createAnimation() {
        Timeline timeline = new Timeline();
        double finalWidth = parentPanel.getMaxWidth();
        double finalHeight = parentPanel.getMaxHeight();

        timeline.getKeyFrames().addAll(
                new KeyFrame(Duration.ZERO,
                        new KeyValue(parentPanel.maxWidthProperty(), 0),
                        new KeyValue(parentPanel.maxHeightProperty(), 0)
                ),
                new KeyFrame(new Duration(1000),
                        new KeyValue(parentPanel.maxWidthProperty(), finalWidth),
                        new KeyValue(parentPanel.maxHeightProperty(), finalHeight)
                )
        );
        return timeline;
    }

    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, Duke.getUser().getName(), img, Color.TURQUOISE);
    }

    public static DialogBox getDukeDialog(String text, Image img) {
        return new DialogBox(text, "Duke", img, Color.BURLYWOOD, true);
    }
}
