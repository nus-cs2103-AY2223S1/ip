package duke.fxwindows;

import duke.TaskList;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class TaskCategoryPane extends VBox {


    private final Window parent;

    TaskCategoryPane(Window parent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/TaskCategoryPane.fxml"));
            fxmlLoader.setController(this);
//            fxmlLoader.setRoot(this);
            fxmlLoader.load();

        } catch (IOException e) {
            e.printStackTrace();
        }

        this.addLabels();
        this.parent = parent;        

//        HBox.setHgrow(this, Priority.ALWAYS);

        this.getStyleClass().add("pane");
        this.getStyleClass().add("categoryPane");
    }
    
    private void addLabels() {
        TaskCategoryLabel allLabel = new TaskCategoryLabel("All");
        allLabel.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                parent.updateTaskList(parent.duke.getTasks());
            }
        });
        TaskCategoryLabel todoLabel = new TaskCategoryLabel("TODOs");
        todoLabel.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                parent.updateTaskList(
                        parent.duke.getTasks().filterByCategory(TaskList.Categories.TODO));
            }
        });
        TaskCategoryLabel eventLabel = new TaskCategoryLabel("Event");
        eventLabel.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                parent.updateTaskList(
                        parent.duke.getTasks().filterByCategory(TaskList.Categories.EVENT));
            }
        });
        TaskCategoryLabel deadlineLabel = new TaskCategoryLabel("Deadline");
        deadlineLabel.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                parent.updateTaskList(
                        parent.duke.getTasks().filterByCategory(TaskList.Categories.DEADLINE));
            }
        });

        this.getChildren().addAll(allLabel, todoLabel, eventLabel, deadlineLabel);
    }

    private static class TaskCategoryLabel extends TaskLabel {
        public TaskCategoryLabel(String str) {
            super(str);
            this.getStyleClass().add("hoverableText");
        }
    }

}
