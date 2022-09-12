package duke.fxwindows;

import duke.TaskList;
import duke.tasks.Task;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.Iterator;

public class TaskListPane extends VBox {

    private TaskList tasks;
    private Window parent;

    public TaskListPane(Window parent, TaskList tasks) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/TaskListPane.fxml"));
            fxmlLoader.setController(this);
//            fxmlLoader.setRoot(this);
            fxmlLoader.load();

        } catch (IOException e) {
            e.printStackTrace();
        }
        HBox.setHgrow(this, Priority.ALWAYS);

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

    private void refresh() {
        this.getChildren().clear();
        for (Iterator<Task> it = this.tasks.getIterator(); it.hasNext(); ) {
            Task t = it.next();

            Label l = new TaskCategoryLabel(t.toString());
            l.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    parent.selectTask(t);
                }
            });

            this.getChildren().add(l);
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
