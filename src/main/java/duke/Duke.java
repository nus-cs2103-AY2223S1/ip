package duke;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import duke.chatbot.ChatBot;
import duke.gui.MainWindow;
import java.util.Scanner;
public class Duke extends Application {
    private static final double WINDOW_MIN_WIDTH = 400.0;
    private static final double WINDOW_MIN_HEIGHT = 600.0;
    private Scene scene;


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ChatBot christina = new ChatBot("Christina");
        christina.initialize();
        while (christina.isRunning()) {
            christina.processCommand(scanner.nextLine());
        }
        scanner.close();
        christina.terminate();
    }
    @Override
    public void start(Stage stage) {
        // Setting the scene
        MainWindow mainWindow = new MainWindow(new ChatBot("Christina"), WINDOW_MIN_WIDTH, WINDOW_MIN_HEIGHT);
        mainWindow.initialize();

        scene = new Scene(mainWindow);

        // Styling the scene
        stage.setTitle("DukePro");
        stage.setResizable(false);
        stage.setMinWidth(WINDOW_MIN_WIDTH);
        stage.setMinHeight(WINDOW_MIN_HEIGHT);

        stage.setScene(scene);
        stage.show();
    }
}
