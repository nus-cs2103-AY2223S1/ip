package duke.fxwindows;

import duke.TaskList;
import duke.tasks.Task;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.HashMap;

public class TaskListPane extends ScrollPane {

    private Task selectedTask;
    private TaskList tasks;
    private HashMap<Task, Label> taskToLabelMap;

    private Window parent;

    @FXML
    private VBox vBox;

    public TaskListPane(Window parent, TaskList tasks) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/TaskListPane.fxml"));
            fxmlLoader.setRoot(this);
            fxmlLoader.setController(this);
            fxmlLoader.load();

        } catch (IOException e) {
            e.printStackTrace();
        }

        this.setTasks(tasks);
        this.parent = parent;
        this.refresh();

        this.getStyleClass().add("pane");
        this.getStyleClass().add("listPane");

    }

    void setTasks(TaskList t) {
        this.tasks = t;
        this.refresh();
    }

     void refresh() {
        this.vBox.getChildren().clear();
        this.taskToLabelMap = new HashMap<>();
        for (int i = 0; i < this.tasks.size(); i ++) {
            Task t = this.tasks.get(i);
            Label l = new TaskCategoryLabel((i+1) + ". " + t.toString());

            this.taskToLabelMap.put(t, l);

            l.setOnMouseClicked((MouseEvent event) -> {
                parent.selectTask(t);
            });

            this.vBox.getChildren().add(l);
        }
    }

    private static class TaskCategoryLabel extends TaskLabel {
        public TaskCategoryLabel(String str) {
            super(str);

            // CSS Classes
            this.getStyleClass().add("hoverableLabel");
        }
    }

    void deSelectTaskFromParent(){
        if (selectedTask != null) {
            Label prevTaskLabel = this.taskToLabelMap.get(this.selectedTask);
            ObservableList<String> styles = prevTaskLabel.getStyleClass();
            styles.remove(styles.size() - 1);
        }
        selectedTask = null;
    }
    void selectTaskFromParent(Task t) {
        deSelectTaskFromParent();
        if (t == null) {
            return;
        }
        this.taskToLabelMap.get(t).getStyleClass().add("selectedFill");
        this.selectedTask = t;
    }
}
