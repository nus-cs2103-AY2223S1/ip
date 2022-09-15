package cwq;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private final CWQ cwq = new CWQ();
    private final String logo =
              " _______ \t                     \t    ________ \n"
            + "|      \\ \t  \\      /\\      /\t   |        |\n"
            + "|          \t  \\    /  \\    / \t   |        |\n"
            + "|           \t  \\  /    \\  /  \t   |        |\n"
            + "|_______/    \t  \\/      \\/   \t   |_______\\\n"
            + "Hi, I'm cwq. What can I do for you?";
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image cwqImage = new Image(this.getClass().getResourceAsStream("/images/cwq.png"));
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(cwq);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
