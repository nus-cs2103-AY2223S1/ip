package duke;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneController {

    private Stage stage;
    private Scene scene;
    private Duke duke = new Duke();


    public void enterTaskTracker(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));;
        AnchorPane ap = fxmlLoader.load();
        stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(ap);
        stage.setScene(scene);
        fxmlLoader.<MainWindow>getController().setDuke(duke);
        stage.show();
    }


}
