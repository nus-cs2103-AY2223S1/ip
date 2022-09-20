package zupey.gui;

import java.io.File;
import java.io.IOException;

import zupey.Zupey;
import zupey.data.FileStorage;
import zupey.data.IStorage;
import zupey.service.Service;
import zupey.service.Ui;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/** Gui for zupey Application */
public class Gui extends Application {

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Gui.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            scene.getStylesheets().add("https://fonts.googleapis.com/css2?family=Courier+Prime&display=swap");
            stage.setScene(scene);
            stage.setTitle("Zupey");

            Ui ui = new Ui();
            File storageDirectory = new File("./data");
            if (!storageDirectory.exists()) {
                if (!storageDirectory.mkdir()) {
                    ui.receiveMessage("Could not create /data directory");
                }
            }
            IStorage storage = new FileStorage("./data/zupey.txt");
            Service service = new Service(storage, ui);
            Zupey zupey = new Zupey(service);
            fxmlLoader.<MainWindow>getController().setZupey(zupey);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
