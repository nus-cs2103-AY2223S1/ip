package duke.fxwindows;

import duke.Duke;
import duke.TaskList;
import duke.command.Command;
import duke.tasks.Task;
import javafx.fxml.FXML;
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
    private TaskList taskList;

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


    void deselectTask() {
        this.taskListPane.deSelectTaskFromParent();
    }
    void selectTask(Task t) {
        this.taskDescriptionPane.displayTask(t);
        this.taskListPane.selectTaskFromParent(t);
    }

    void updateTaskList(TaskList tList) {
        deselectTask();
        this.taskListPane.setTasks(tList);
        this.taskList = tList;

        if (tList.size() > 0) {
            selectTask(tList.get(0));
        } else {
            selectTask(null);
        }
    }

    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        Command c = duke.ui.readCommand(input);
        String response = duke.execCommand(c, taskList);
        userInput.setText("");
        showNotification(response);
        if (c.returnsTaskList()) {
            updateTaskList(c.getTaskList());
        }
        taskListPane.refresh();
    }

    @FXML
    private void requestFocusOnClick() {
        userInput.requestFocus();
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
