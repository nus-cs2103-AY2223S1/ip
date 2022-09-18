package duke;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private Duke duke = new Duke();

    @Override
    public void start(Stage primaryStage) throws Exception {

            Parent root = FXMLLoader.load(getClass().getResource("/view/MainPage.fxml"));

            //for application icon and title
            Image icon = new Image("/images/LTNS.png");
            primaryStage.getIcons().add(icon);
            primaryStage.setTitle("Long Time No See! (LTNS)");
            primaryStage.setScene(new Scene(root));
            primaryStage.show();

    }
}