package duke.gui;

import java.io.IOException;

import duke.Duke;

import duke.Task;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Gui extends Application {

    private static final String FILE_NAME = "dukeList.txt";
    private final Duke duke = new Duke(FILE_NAME);

//    public String getEmptyListMessage() {
//        return "Your list is empty! Why not add a task to it first?";
//    }
//
//    /**
//     * Sends a success message upon marking a task as done
//     *
//     * @param t The task to be marked
//     * @return Message to display
//     */
//    public String getTaskMarkedMessage(Task t) {
//        return "Okay, I have marked this task as done: \n" + t.toString();
//    }
//
//    /**
//     * Sends a success message upon marking a task as not done
//     *
//     * @param t The task to be marked
//     * @return Message to display
//     */
//    public String getTaskUnmarkedMessage(Task t) {
//        return "Okay, I have marked this task as not done: \n" + t.toString();
//    }
//
//    /**
//     * Sends a success message upon creating a Todo task
//     *
//     * @param t The task created
//     * @param s The total number of tasks in the list of tasks
//     * @return Message to display
//     */
//    public String getToDoCreatedMessage(Task t, int s) {
//        return "Got it! I have added this task to your list:\n  " + t.toString()
//                + "\nNow you have " + s + " tasks in the list.";
//    }
//
//    /**
//     * Sends a success message upon creating a Deadline task
//     *
//     * @param t The task created
//     * @param s The total number of tasks in the list of tasks
//     * @return Message to display
//     */
//    public String getDeadlineCreatedMessage(Task t, int s) {
//        return "Got it! I have added this task to your list:\n  " + t.toString()
//                + "\nNow you have " + s + " tasks in the list.";
//    }
//
//    /**
//     * Sends a success message upon creating an Event task
//     *
//     * @param t The task created
//     * @param s The total number of tasks in the list of tasks
//     * @return Message to display
//     */
//    public String getEventCreatedMessage(Task t, int s) {
//        return "Got it! I have added this task to your list:\n  " + t.toString()
//                + "\nNow you have " + s + " tasks in the list.";
//    }
//
//    /**
//     * Sends a success message upon deleting a task from the list
//     *
//     * @param t The specific task to be deleted
//     * @param s The total number of tasks left in the list of tasks
//     * @return Message to display
//     */
//    public String getTaskDeletedMessage(Task t, int s) {
//        return "Okay, I have removed this task from the list:\n  " + t.toString()
//                + "\nNow you have " + s + " tasks in the list.";
//    }

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Gui.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Duke");
            fxmlLoader.<MainWindow>getController().setDuke(duke);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
