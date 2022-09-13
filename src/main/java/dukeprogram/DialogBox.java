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
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

/**
 * An example of a custom control using FXML.
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class DialogBox extends HBox {

    private static final Color DUKE_COLOR = Color.color(0.15, 0.15, 0.15);
    private static final Color USER_COLOR = Color.color(1, 0.5, 0);

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
    @FXML
    private Widget widget;

    private boolean isDuke;

    protected DialogBox(String dialogText, User user, Color backgroundColor, Widget widget) {
        this(dialogText, user, backgroundColor);
        this.widget = widget;
        dialogLayout.getChildren().add(widget);
    }

    protected DialogBox(String dialogText, User user, Color backgroundColor) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        name.setText(user.getName());
        dialog.setText(dialogText);

        setDisplayPicture(user.getUserImage());

        dialog.setPadding(new Insets(5, 5, 5, 5));

        if (user.equals(User.DUKE)) {
            isDuke = true;
            this.flip();
        }

        this.layoutBoundsProperty().addListener(new ChangeListener<Bounds>() {
            @Override
            public void changed(ObservableValue<? extends Bounds> observable, Bounds oldValue, Bounds newValue) {
                double width = getBoundsInParent().getWidth();
                double height = getBoundsInParent().getHeight();

                TranslateTransition translateTransition = new TranslateTransition(Duration.millis(500),
                        DialogBox.this);
                translateTransition.setFromX((user.equals(User.DUKE) ? -1 : 1) * width / 2);
                translateTransition.setFromY(-height / 2);
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

    /**
     * Creates a dialog box belonging to the user
     * @param text the text to show in the dialog box
     * @param user the user profile who sent the text
     * @return the constructed dialog box
     */
    public static DialogBox ofUser(String text, User user) {
        DialogBox dialogBox = new DialogBox(text, user, USER_COLOR);
        setBackground(dialogBox);
        return dialogBox;
    }

    /**
     * Creates a dialog box belonging to Duke
     * @param text the text to show in the dialog box
     * @return the constructed dialog box
     */
    public static DialogBox ofDuke(String text) {
        DialogBox dialogBox = new DialogBox(text, User.DUKE, DUKE_COLOR);
        setBackground(dialogBox);
        return dialogBox;
    }

    /**
     * Creates a dialog box belonging to Duke
     * @param text the text to show in the dialog box
     * @return the constructed dialog box
     */
    public static DialogBox ofDuke(String text, Widget widget) {
        DialogBox dialogBox = new DialogBox(text, User.DUKE, DUKE_COLOR, widget);
        setBackground(dialogBox);
        return dialogBox;
    }

    private static void setBackground(DialogBox box) {
        double startX;
        double endX;
        double offsetDuke;
        double offsetUser;

        if (box.isDuke) {
            startX = 0.8;
            endX = 1;
            offsetDuke = 0.9;
            offsetUser = 1;
        } else {
            startX = 0;
            endX = 0.2;
            offsetDuke = 0.1;
            offsetUser = 0;
        }

        box.setBackground(new Background(new BackgroundFill(
                new LinearGradient(startX,
                        0.5,
                        endX,
                        0.5,
                        true,
                        CycleMethod.NO_CYCLE,
                        new Stop(offsetDuke, DUKE_COLOR),
                        new Stop(offsetUser, USER_COLOR)
                ),
                new CornerRadii(10),
                new Insets(5, 5, 5, 5)))
        );
    }
}
