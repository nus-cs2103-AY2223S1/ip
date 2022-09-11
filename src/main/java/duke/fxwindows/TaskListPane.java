package duke.fxwindows;

import duke.TaskList;
import duke.tasks.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.Iterator;

public class TaskListPane extends VBox {

    private TaskList tasks;

    public TaskListPane(TaskList tasks) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/TaskListPane.fxml"));
            fxmlLoader.setController(this);
//            fxmlLoader.setRoot(this);
            fxmlLoader.load();

        } catch (IOException e) {
            e.printStackTrace();
        }
        HBox.setHgrow(this, Priority.ALWAYS);

        this.tasks = tasks;
        this.addTasks();

    }

    private void addTasks() {
        for (Iterator<Task> it = this.tasks.getIterator(); it.hasNext(); ) {
            Task t = it.next();
            this.getChildren().add(taskToLabel(t));
        }
    }

    private static Label taskToLabel(Task t) {
        return new Label(t.toString());
    }
}
