import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import java.io.IOException;
import java.util.Collections;

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

public class MessageContainer extends HBox {
    @FXML
    private Label message;

    @FXML
    private ImageView profile;

    private MessageContainer(String message, Image profile) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/MessageContainer.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.message.setText(message);
        this.profile.setImage(profile);
//        this.message = message;
//        this.profile = profile;

//        message.setWrapText(true);
//        profile.setFitWidth(100.0);
//        profile.setFitHeight(100.0);
//
//        this.setAlignment(Pos.TOP_RIGHT);
//        this.getChildren().addAll(message, profile);
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
}
