package duke.fxwindows;

import duke.Duke;
import duke.TaskList;
import duke.tasks.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

public class Window extends AnchorPane {

    @FXML
    private HBox contentBox;
    @FXML
    private TextField userInput;
    @FXML
    private Button helpButton;

    Duke duke;

    private TaskCategoryPane taskCategoryPane;
    private TaskListPane taskListPane;
    private TaskDescriptionPane taskDescriptionPane;

    public void initialise(Duke duke) {
//        Setup duke
        this.duke = duke;
        this.duke.init();

//        Setup panes
        this.loadTaskCategoryPane();
        this.loadTaskListPane();
        this.loadTaskDescPane();
    }

    private void loadTaskCategoryPane() {
        this.taskCategoryPane = new TaskCategoryPane(this);
        this.contentBox.getChildren().add(this.taskCategoryPane);
    }

    private void loadTaskListPane() {
        this.taskListPane = new TaskListPane(this, this.duke.getTasks());
        this.contentBox.getChildren().add(this.taskListPane);
    }

    private void loadTaskDescPane() {
        this.taskDescriptionPane = new TaskDescriptionPane(this.duke.getTasks().get(0));
        this.contentBox.getChildren().add(this.taskDescriptionPane);
    }

    void selectTask(Task t) {
        this.taskDescriptionPane.displayTask(t);
    }

    void updateTaskList(TaskList tList) {
        this.taskListPane.setTasks(tList);
    }

    void updateTaskList(){
        this.taskListPane.setTasks(this.duke.getTasks());
    }

    @FXML
    public void handleUserInput() {
        String input = userInput.getText();
        String response = duke.execCommand(input);
        System.out.println(response);
        userInput.setText("");
        this.updateTaskList();
    }
}
