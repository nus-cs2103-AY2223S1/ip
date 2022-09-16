package duke.fxwindows;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class HelpWindow extends AnchorPane {

//    @FXML
//    private
    HelpWindow() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/HelpWindow.fxml"));
            fxmlLoader.setController(this);
//            fxmlLoader.setRoot(this);
            AnchorPane ap = fxmlLoader.load();

            Stage stage = new Stage();
            stage.setTitle("Duke Help");
            stage.setScene(new Scene(ap, 450, 450));
            stage.setAlwaysOnTop(true);
            stage.show();


            stage.addEventHandler(KeyEvent.KEY_RELEASED, (KeyEvent event) -> {
                if(event.getCode() == KeyCode.ESCAPE) {
                    stage.close();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
