package duke.chatbot;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class Test extends Application {
    @Override
    public void start(Stage stage) {
        Label helloWorld = new Label("Hello world");
        Scene scene = new Scene(helloWorld);

        stage.setScene(scene);
        stage.show();
    }
}
