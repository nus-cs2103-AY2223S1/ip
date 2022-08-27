package maria.ui;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import maria.TaskManager;
import maria.command.Command;
import maria.command.CommandFindTask;
import maria.task.Task;
import maria.task.TaskList;

/**
 * Represents the controller for the main page of the application.
 */
public class LandingPage extends VBox {

    private TaskManager taskManager;

    @FXML
    private Button buttonNewTodo;

    @FXML
    private Button buttonNewDeadline;

    @FXML
    private Button buttonNewEvent;

    @FXML
    private Button buttonSearch;

    @FXML
    private TextField textFieldSearch;

    @FXML
    private VBox vBoxTasks;

    /**
     * Creates a LandingPage object with the associated task manager.
     * @param taskManager The task manager, to be used throughout the UI system.
     */
    public LandingPage(TaskManager taskManager) {

        try {

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("landing_page.fxml"));
            fxmlLoader.setRoot(this);
            fxmlLoader.setController(this);
            fxmlLoader.load();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        this.taskManager = taskManager;
        populateExistingTasks();

    }

    /**
     * Refreshes the tasks in the tasks VBox.
     *
     * This function is to be called after any modification to the tasks.
     */
    public void refreshTasks() {

        this.vBoxTasks.getChildren().clear();
        populateExistingTasks();

    }

    /**
     * Initializes the existing tasks into the tasks VBox.
     */
    private void populateExistingTasks() {

        TaskList tasks = this.taskManager.getTaskList();

        for (Task task : tasks) {
            this.vBoxTasks.getChildren().add(new TaskItem(this, this.taskManager, task));
        }

    }

    /**
     * Opens a AddTodo screen.
     */
    @FXML
    private void buttonNewTodoClicked() {
        showAddTaskWindow(new AddTodo(this, this.taskManager), "Add Todo");
    }

    /**
     * Opens a AddDeadline screen.
     */
    @FXML
    private void buttonNewDeadlineClicked() {
        showAddTaskWindow(new AddDeadline(this, this.taskManager), "Add Deadline");
    }

    /**
     * Opens a AddEvent screen.
     */
    @FXML
    private void buttonNewEventClicked() {
        showAddTaskWindow(new AddEvent(this, this.taskManager), "Add Event");
    }

    /**
     * Executes the find task command and displays the result in a new window.
     */
    @FXML
    private void buttonSearchClicked() {

        Command command = new CommandFindTask(this.textFieldSearch.getText());
        command.execute(this.taskManager);

        TasksFindResult tasksFindResult = new TasksFindResult(this, this.taskManager);

        Stage stage = new Stage();
        stage.setScene(new Scene(tasksFindResult));
        stage.setTitle("Find Results");
        stage.show();

    }

    private void showAddTaskWindow(AnchorPane window, String title) {

        Stage stage = new Stage();
        stage.setScene(new Scene(window));
        stage.setTitle(title);
        stage.show();

    }

}