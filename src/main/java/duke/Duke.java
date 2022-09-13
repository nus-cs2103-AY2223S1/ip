package duke;

import java.util.Scanner;

import duke.chatbot.ChatBot;
import duke.gui.MainWindow;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Duke class that runs the main program.
 */
public class Duke extends Application {
    private static final double WINDOW_MIN_WIDTH = 360.0;
    private static final double WINDOW_MIN_HEIGHT = 480.0;
    private static final double STAGE_WIDTH_OFFSET = 15.0;
    private static final double STAGE_HEIGHT_OFFSET = 40.0;

    /**
     * main
     * @param args system arguments
     */
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
        MainWindow mainWindow = new MainWindow(new ChatBot("Christina"));
        mainWindow.initialize();
        mainWindow.setMinSize(WINDOW_MIN_WIDTH, WINDOW_MIN_HEIGHT);

        Scene scene = new Scene(mainWindow);

        // Styling the scene
        stage.setTitle("DukePro");
        stage.setResizable(true);
        stage.setMinWidth(WINDOW_MIN_WIDTH + STAGE_WIDTH_OFFSET);
        stage.setMinHeight(WINDOW_MIN_HEIGHT + STAGE_HEIGHT_OFFSET);

        stage.setScene(scene);
        stage.show();
    }
}
