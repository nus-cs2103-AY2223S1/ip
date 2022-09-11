package duke.fxwindows;

import duke.TaskList;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class TaskDescriptionPane extends VBox {

    private Task currentTask;

    public TaskDescriptionPane(Task t) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/TaskDescriptionPane.fxml"));
            fxmlLoader.setController(this);
//            fxmlLoader.setRoot(this);
            fxmlLoader.load();

        } catch (IOException e) {
            e.printStackTrace();
        }
        HBox.setHgrow(this, Priority.ALWAYS);

        this.currentTask = t;
        this.renderTask();

    }

    private void renderTask() {
        Task t = currentTask;

        if (t instanceof Event) {
            this.getChildren().add(new Label(t.toString()));
        } else if (t instanceof Deadline) {
            this.getChildren().add(new Label(t.toString()));
        } else if (t instanceof Todo) {
            this.getChildren().add(new Label(t.toString()));
        } else {
            this.getChildren().add(new Label("Unknown task!"));
        }
    }
}
