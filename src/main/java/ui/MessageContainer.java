package ui;

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
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class MessageContainer extends HBox {
    @FXML
    private Text message;

    @FXML
    private Label label;

    @FXML
    private ImageView profile;

    @FXML
    private Circle circleProfile;

    private MessageContainer(String message, Image profile) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/MessageContainer.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        circleProfile.setFill(new ImagePattern(profile));
        this.message.setText(message);
    }

    private void setBotStyle() {
        this.label.setStyle(
                "-fx-background-color: #FFFF99; " +
                "-fx-background-radius: 10px; " +
                "-fx-padding: 10px; " +
                "-fx-max-height: 120px; " +
                "-fx-font-size: 13px;" +
                "-fx-font-family: 'Droid Sans';"
        );
        this.message.setTextAlignment(TextAlignment.LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }

    public static MessageContainer getUserMessage(String message, Image profile) {
        return new MessageContainer(message, profile);
    }

    public static MessageContainer getBotMessage(String message, Image profile) {
        var botMessageContainer = new MessageContainer(message, profile);
        botMessageContainer.setBotStyle();
        return botMessageContainer;
    }

    public static MessageContainer getIntroduction(Image profile) {
        var introMessageContainer = new MessageContainer(Ui.printIntroduction(), profile);
        introMessageContainer.setBotStyle();
        return introMessageContainer;
    }
}
