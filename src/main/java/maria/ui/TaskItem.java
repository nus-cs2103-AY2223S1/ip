package maria.ui;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import maria.TaskManager;
import maria.command.CommandMarkTask;
import maria.command.CommandRemoveTask;
import maria.command.CommandUnmarkTask;
import maria.task.Task;

/**
 * Provides the controller for the pane for displaying a row of task item.
 */
public class TaskItem extends AnchorPane {

    private LandingPage landingPage;
    private TaskManager taskManager;
    private Task task;

    @FXML
    private Hyperlink hyperlinkDeleteTask;

    @FXML
    private CheckBox checkBoxDone;

    @FXML
    private Label labelTaskName;

    @FXML
    private Label labelTaskInfo;

    /**
     * Creates a TaskItem row.
     * @param landingPage The landing page controller object
     * @param taskManager The task manager object
     * @param task The task to be represented by the TaskItem
     */
    public TaskItem(LandingPage landingPage, TaskManager taskManager, Task task) {

        try {

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("task_item.fxml"));
            fxmlLoader.setRoot(this);
            fxmlLoader.setController(this);
            fxmlLoader.load();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        this.landingPage = landingPage;
        this.taskManager = taskManager;
        this.task = task;

        this.checkBoxDone.setSelected(task.getIsDone());
        this.labelTaskName.setText(this.task.getName());
        this.labelTaskInfo.setText(this.task.toString());

    }

    /**
     * Executes the mark and unmark commands on check and uncheck respectively.
     */
    @FXML
    private void checkBoxTaskDoneCheckedChanged() {

        if (this.checkBoxDone.isSelected()) {
            CommandMarkTask command = new CommandMarkTask(this.task);
            command.execute(this.taskManager);
        } else {
            CommandUnmarkTask command = new CommandUnmarkTask(this.task);
            command.execute(this.taskManager);
        }

    }

    /**
     * Executes the delete command when clicked.
     */
    @FXML
    private void hyperlinkDeleteTaskClicked() {

        CommandRemoveTask command = new CommandRemoveTask(this.task);
        command.execute(this.taskManager);
        this.landingPage.refreshTasks();

    }

}
