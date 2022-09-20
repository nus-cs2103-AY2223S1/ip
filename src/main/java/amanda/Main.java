package amanda;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import amanda.gui.MainWindow;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

	private final Amanda amanda = new Amanda("data/amanda.txt");

	@Override
	public void start(Stage stage) {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
			AnchorPane ap = fxmlLoader.load();
			Scene scene = new Scene(ap);
			stage.setScene(scene);
			stage.setTitle("Amanda");
			fxmlLoader.<MainWindow>getController().setAmanda(amanda);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
