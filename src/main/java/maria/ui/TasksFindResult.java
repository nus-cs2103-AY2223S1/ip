package maria.ui;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import maria.TaskManager;

/**
 * Provides the controller for the window for display the result of a task search.
 */
public class TasksFindResult extends AnchorPane {

    @FXML
    private Label labelTasks;

    public TasksFindResult(LandingPage landingPage, TaskManager taskManager) {

        try {

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("tasks_find_result.fxml"));
            fxmlLoader.setRoot(this);
            fxmlLoader.setController(this);
            fxmlLoader.load();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        String tasksString = taskManager.getTaskListFindResultString();
        this.labelTasks.setText(tasksString);

    }

}
