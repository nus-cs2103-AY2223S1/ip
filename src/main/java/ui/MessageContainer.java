package ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

public class MessageContainer extends HBox {
    @FXML
    private Label message;

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

    private void moveLeft() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }

    public static MessageContainer getUserMessage(String message, Image profile) {
        return new MessageContainer(message, profile);
    }

    public static MessageContainer getBotMessage(String message, Image profile) {
        var botMessageContainer = new MessageContainer(message, profile);
        botMessageContainer.moveLeft();
        return botMessageContainer;
    }

    public static MessageContainer getIntroduction(Image profile) {
        var introMessageContainer = new MessageContainer(Ui.printIntroduction(), profile);
        introMessageContainer.moveLeft();
        return introMessageContainer;
    }
}
