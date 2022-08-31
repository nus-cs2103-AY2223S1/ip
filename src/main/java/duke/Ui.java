package duke;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Ui class containing logic for receiving user input and printing output
 */
public class Ui extends Application {

    private static final String GREETINGS = "Hello! I'm Ekud \n" + "What can I do for you?";
    private final Duke duke = new Duke();
    private MainWindow mainWindow;

    /**
     * Starts the GUI.
     * @param stage The stage parameter set by JavaFX.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(
                    Ui.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(duke);
            mainWindow = fxmlLoader.<MainWindow>getController();
            mainWindow.setDuke(duke);
            printGreetings();
            if (duke.getLoaded()) {
                print("File successfully loaded!");
            } else {
                print("Error parsing load file");
            }
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void print(String msg) {
        mainWindow.print(msg);
    }
    /**
     * Prints greetings message.
     */
    public void printGreetings() {
        print(GREETINGS);
    }

    /**
     * Prints message when task is added.
     *
     * @param msg  Description of task added.
     * @param size Size of task list.
     * @return Add task message.
     */
    public static String getAddTaskString(String msg, int size) {
        return "Got it. I've added this task:\n" + msg
                + "\nNow you have " + size + " tasks in the list.";
    }

    /**
     * Returns entire task list.
     *
     * @param tasks TaskList to be printed.
     * @return Entire task list.
     */
    public static String getListString(TaskList tasks) {
        String list = "";
        for (int i = 0; i < tasks.size(); i++) {
            list += (i + 1) + "." + tasks.get(i);
            if (i != tasks.size() - 1) {
                list += "\n";
            }
        }
        return list;
    }
}
