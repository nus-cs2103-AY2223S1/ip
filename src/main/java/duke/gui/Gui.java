package duke.gui;

import java.io.File;
import java.io.IOException;

import duke.Duke;
import duke.data.FileStorage;
import duke.data.IStorage;
import duke.service.Service;
import duke.service.Ui;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/** Gui for Duke Application */
public class Gui extends Application {

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Gui.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            Ui ui = new Ui();
            File storageDirectory = new File("./data");
            if (!storageDirectory.exists()) {
                if (!storageDirectory.mkdir()) {
                    ui.receiveMessage("Could not create /data directory");
                }
            }
            IStorage storage = new FileStorage("./data/duke.txt");
            Service service = new Service(storage, ui);
            Duke duke = new Duke(service);
            fxmlLoader.<MainWindow>getController().setDuke(duke);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
