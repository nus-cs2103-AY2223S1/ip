package duke.components;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

/**
 * Custom Header for chat box.
 */
public class Header extends HBox {
    @FXML
    private Label username;
    @FXML
    private Circle avatar;
    @FXML
    private ImageView icon;
    private Header(String name, Image img, Image icon) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/Header.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.icon.setImage(icon);
        this.username.setText(name);
        avatar.setFill(new ImagePattern(img));
    }
    public static Header setHeader(String username, Image img, Image icon) {
        Header h = new Header(username, img, icon);
        h.setAlignment(Pos.TOP_LEFT);
        return h;
    }
}
