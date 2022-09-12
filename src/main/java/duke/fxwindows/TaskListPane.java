package duke.fxwindows;

import duke.TaskList;
import duke.tasks.Task;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

import static java.lang.Double.MAX_VALUE;

public class TaskListPane extends VBox {

    private TaskList tasks;
//    We have a hashmap here so that when you click on a label, you get a reference to a task
    private HashMap<Label, Task> taskLabelMap= new HashMap<>();

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


        this.getStyleClass().add("pane");
        this.getStyleClass().add("listPane");

    }

    private void addTasks() {
        for (Iterator<Task> it = this.tasks.getIterator(); it.hasNext(); ) {
            Task t = it.next();
            this.getChildren().add(taskToLabel(t));
        }
    }

    private static Label taskToLabel(Task t) {
        return new TaskCategoryLabel(t.toString());
    }

    private static class TaskCategoryLabel extends TaskLabel {
        public TaskCategoryLabel(String str) {
            super(str);

            // CSS Classes
            this.getStyleClass().add("hoverableLabel");
        }
    }
}
