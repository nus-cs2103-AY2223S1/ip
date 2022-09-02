package main;

import duke.Duke;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import ui.MainWindow;
import ui.Ui;

import java.io.IOException;
public class Main extends Application {
    private Ui ui;

    @Override
    public void start(Stage stage) throws Exception {
        try {
            ui = new Ui();
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane mainWindow = fxmlLoader.load();
            Scene scene = new Scene(mainWindow);
            scene.getRoot().setStyle("-fx-font-family: 'serif'");
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(ui);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
