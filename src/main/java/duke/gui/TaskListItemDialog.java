package duke.gui;

import static java.util.Objects.nonNull;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.annotation.Nullable;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskType;
import duke.task.ToDo;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * This control represents a dialog box for a task list item.
 */
public class TaskListItemDialog extends HBox {
    @FXML
    private HBox root;
    @FXML
    private Rectangle taskStatus;
    @FXML
    private ImageView checkbox;
    @FXML
    private Label taskDescription;
    @FXML
    private Label badge;
    @FXML
    private ImageView dateIcon;
    @FXML
    private Label date;
    @FXML
    private HBox dateSection;
    @FXML
    private VBox taskDetails;
    @FXML
    private Label taskNumber;

    private Image emptyCheckbox = new Image(this.getClass().getResourceAsStream("/images/not done task.png"));
    private Image checkedCheckbox = new Image(this.getClass().getResourceAsStream("/images/done task.png"));
    private Image alarmClock = new Image(this.getClass().getResourceAsStream("/images/alarm clock icon.png"));
    private Image calendar = new Image(this.getClass().getResourceAsStream("/images/calendar icon.png"));

    private TaskListItemDialog(TaskType taskType, String description, boolean completed,
                               Task task, @Nullable Integer taskNumber, @Nullable LocalDateTime completionDate) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/TaskListItem.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        switch (taskType) {
        case TODO:
            badge.setText("todo");
            badge.setStyle("-fx-background-color: rgb(142, 176, 206); -fx-background-radius: 8;");
            dateSection.setVisible(false);
            dateSection.setMinHeight(0);
            dateSection.setMaxHeight(0);
            taskDetails.setTranslateY(5);
            break;
        case DEADLINE:
            Deadline dl = (Deadline) task;
            badge.setText("deadline");
            badge.setStyle("-fx-background-color: rgb(221, 165, 115); -fx-background-radius: 8;");
            badge.setMinWidth(56);
            dateIcon.setImage(alarmClock);
            date.setText(dl.getDueDatetimeString());
            date.setMinHeight(Region.USE_PREF_SIZE);
            dateSection.setMinHeight(Region.USE_PREF_SIZE);
            break;
        case EVENT:
            Event e = (Event) task;
            badge.setText("event");
            badge.setStyle("-fx-background-color: rgb(189, 206, 142); -fx-background-radius: 8;");
            dateIcon.setImage(calendar);
            date.setText(e.getStartDatetimeString() + " to " + e.getEndDatetimeString());
            date.setMinHeight(Region.USE_PREF_SIZE);
            dateSection.setMinHeight(Region.USE_PREF_SIZE);
            break;
        default:
        }

        if (completed) {
            checkbox.setFitWidth(19);
            checkbox.setImage(checkedCheckbox);
            taskStatus.setFill(Color.rgb(0, 145, 124));
            String readableDateString = completionDate.format(DateTimeFormatter.ofPattern("dd MMM yyyy hh:mm a"));
            String completionDateString = String.format("Task completed on %s", readableDateString);
            Tooltip.install(checkbox, new Tooltip(completionDateString));
        } else {
            checkbox.setImage(emptyCheckbox);
            taskStatus.setFill(Color.rgb(255, 200, 61));
        }

        if (nonNull(taskNumber)) {
            this.taskNumber.setText(taskNumber + ". ");
            this.taskNumber.widthProperty().addListener((obs, oldVal, newVal) -> {
                dateSection.setTranslateX(newVal.doubleValue() + 10);
            });
        } else {
            this.taskNumber.setVisible(false);
            this.taskDescription.setTranslateX(-20);
        }

        taskDescription.setText(description);
        taskDescription.setMinHeight(Region.USE_PREF_SIZE);
        taskDetails.heightProperty().addListener((obs, oldVal, newVal) -> {
            taskStatus.setHeight(newVal.intValue());
            root.setMinHeight(10 + newVal.intValue());
        });
    }

    public static TaskListItemDialog getTaskListItemDialog(Task task, @Nullable Integer taskNumber) {
        String description = task.getDescription();
        boolean completed = task.isCompleted();
        LocalDateTime completionDate = task.getCompletionDateTime();

        if (task instanceof ToDo) {
            return new TaskListItemDialog(TaskType.TODO, description, completed, task, taskNumber, completionDate);
        } else if (task instanceof Event) {
            return new TaskListItemDialog(TaskType.EVENT, description, completed, task, taskNumber, completionDate);
        } else {
            return new TaskListItemDialog(TaskType.DEADLINE, description, completed, task, taskNumber, completionDate);
        }
    }
}
