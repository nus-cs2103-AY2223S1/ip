package general.ui;

import java.io.IOException;

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
import mia.Main;

public class ChatBubble extends HBox {
    @FXML
    private Label text;
    @FXML
    private ImageView avatar;

    public enum MessageType {
        COMMAND,
        RESPONSE,
    }

    private ChatBubble(String text, Image avatar) {
        try {
            final FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/ChatBubble.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.text.setText(text);
        this.avatar.setImage(avatar);
    }

    public static ChatBubble fromCommand(String command, Image avatar) {
        return new ChatBubble(command, avatar);
    }

    public static ChatBubble fromResponse(String response, Image avatar) {
        final ChatBubble chatBubble = new ChatBubble(response, avatar);
        chatBubble.flip();
        return chatBubble;
    }

    private void flip() {
        ObservableList<Node> children = FXCollections.observableArrayList(getChildren());
        FXCollections.reverse(children);
        getChildren().setAll(children);
        setAlignment(Pos.TOP_LEFT);
    }
}
