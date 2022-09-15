package skylark.main;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import skylark.skylark.Skylark;
import skylark.ui.MainWindow;

/**
 * A GUI for Skylark using FXML.
 */
public class Main extends Application {

    private final Skylark skylark = new Skylark();
    private final InputStream imageStream = this.getClass().getResourceAsStream("/images/User.png");
    private final Image userImage = new Image(Objects.requireNonNull(imageStream));

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setSkylark(skylark);
            fxmlLoader.<MainWindow>getController().setStage(stage);
            stage.getIcons().add(userImage);
            stage.setTitle(Skylark.TITLE);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
