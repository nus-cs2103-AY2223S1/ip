package dukeprogram;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javafx.animation.ParallelTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
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
    private Label name;
    @FXML
    private Label dialog;
    @FXML
    private Label date;
    @FXML
    private VBox dialogLayout;
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

        name.getStyleClass().add("header");
        dialog.getStyleClass().add("regular");
        date.getStyleClass().add("tag");

        name.setText(user.getName());
        dialog.setText(dialogText);
        dialog.setMinWidth(Region.USE_PREF_SIZE);
        dialog.widthProperty().addListener(c -> {
            dialog.setMinWidth(Math.min(dialog.getWidth(), dialogLayout.getMaxWidth()));
        });

        dialog.setPadding(new Insets(0, 0, 0, 20));
        date.setPrefWidth(Double.MAX_VALUE);
        date.setText(LocalDateTime.now().format(DateTimeFormatter.ofPattern("hh:mm a")));

        if (user.equals(User.DUKE)) {
            isDuke = true;
            this.flip();
        }
        dialogLayout.minWidthProperty().bind(dialog.widthProperty());
        createAnimation(user);
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        setAlignment(Pos.TOP_LEFT);
        dialog.setPadding(new Insets(0, 20, 0, 0));
        dialogLayout.setAlignment(Pos.TOP_LEFT);
        dialog.setAlignment(Pos.TOP_LEFT);
        date.setAlignment(Pos.CENTER_RIGHT);
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

        box.dialogLayout.setBackground(new Background(new BackgroundFill(
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
                Insets.EMPTY))
        );
    }

    private void createAnimation(User user) {
        this.layoutBoundsProperty().addListener(new ChangeListener<>() {
            @Override
            public void changed(ObservableValue<? extends Bounds> observable,
                                Bounds oldValue, Bounds newValue) {
                double width = getBoundsInParent().getWidth();
                double height = getBoundsInParent().getHeight();

                TranslateTransition translateTransition =
                        new TranslateTransition(Duration.millis(500), DialogBox.this);
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
}
