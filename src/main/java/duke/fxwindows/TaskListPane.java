package duke.fxwindows;

import duke.TaskList;
import duke.tasks.Task;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.Iterator;

public class TaskListPane extends ScrollPane {

    private TaskList tasks;
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
        for (int i = 0; i < this.tasks.size(); i ++) {
            Task t = this.tasks.get(i);

            Label l = new TaskCategoryLabel((i+1) + ". " + t.toString());
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
}
