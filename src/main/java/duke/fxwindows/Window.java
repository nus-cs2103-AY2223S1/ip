package duke.fxwindows;

import duke.Duke;
import duke.TaskList;
import duke.tasks.Task;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import org.controlsfx.control.NotificationPane;

public class Window extends AnchorPane {

    @FXML
    private ToolBar toolBar;
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
        this.loadToolBar();
        this.loadTaskCategoryPane();
        this.loadTaskListPane();
        this.loadTaskDescPane();
        this.loadBottomBar();


    }

    private void loadToolBar() {
        this.toolBar.getStyleClass().add("toolbar");
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

    private void loadBottomBar() {
        this.userInput.getStyleClass().add("commandField");
    }


    void selectTask(Task t) {
        this.taskDescriptionPane.displayTask(t);
    }

    void updateTaskList(TaskList tList) {
        this.taskListPane.setTasks(tList);
    }

    void updateTaskList() {
        this.taskListPane.setTasks(this.duke.getTasks());
    }

    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = duke.execCommand(input);
        userInput.setText("");
        showNotification(response);
        this.updateTaskList();
    }

    @FXML
    private void showHelpWindow() {
        new HelpWindow();
    }

    void showNotification(String text) {
        NotificationPane np = getNotificationPane();
        np.setText(text);
        np.setCloseButtonVisible(false);
        np.setShowFromTop(false);

        np.setOnMouseClicked((MouseEvent event) -> {
            np.hide();
        });

        np.setOnKeyReleased((KeyEvent event) -> {
            if(event.getCode() != KeyCode.ENTER) {
                np.hide();
            }
        });
        np.show();
    }

    private NotificationPane getNotificationPane() {
        return (NotificationPane) taskListPane.getScene().getRoot();
    }
}
