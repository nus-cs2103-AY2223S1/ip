package duke.ui;

import java.io.IOException;

import duke.tasks.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

/**
 * This control represents a task item consisting labels that describe the task.
 *
 * @author sikai00
 */
public class TaskItem extends GridPane {
    @FXML
    private Label index;

    @FXML
    private Label description;

    @FXML
    private Label isDone;

    @FXML
    private Label time;

    @FXML
    private Label type;

    private TaskItem(Task task) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/TaskItem.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        index.setText("");
        description.setText(task.getDescription());
        isDone.setText(task.getIsDone() ? "DONE" : "NOT DONE");
        isDone.setTextFill(task.getIsDone() ? Color.color(0, 0.7, 0.4) : Color.color(1.0, 0, 0));
        time.setText(task.getTime().map(t -> t.format(Task.FORMATTER)).orElse("No due date"));
        type.setText(task.getTaskWord());
    }

    public static TaskItem getTaskItem(Task task) {
        return new TaskItem(task);
    }

    public void setIndex(int index) {
        this.index.setText(index + 1 + ")");
    }
}
