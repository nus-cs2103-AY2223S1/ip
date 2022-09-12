package duke.fxwindows;

import duke.tasks.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.HashMap;

public class TaskDescriptionPane extends AnchorPane {

    @FXML
    private VBox vBox;

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

//        I don't know WHY, but for some godforsaken reason the vbox only shows when i add this
//        even though vbox is alr a child in scenebuilder. WTF?
        this.getChildren().add(vBox);
        this.renderTask();

        this.getStyleClass().add("pane");
        this.getStyleClass().add("descriptionPane");

    }

    private void renderTask() {
        vBox.getChildren().clear();

        System.out.println("Current task: " + currentTask.toString());
        if (currentTask == null) {
            vBox.getChildren().add(
                    new TaskLabel("No Task Selected!")
            );
        }
        HashMap<String, String> infoPair = currentTask.getInfoPair();
//        System.out.println(infoPair);
        for (String key : infoPair.keySet()) {
            System.out.println(key + ": " + infoPair.get(key));
            vBox.getChildren().add(
                    new TaskLabel(key + ": " + infoPair.get(key))
            );
        }
    }

    void displayTask(Task t) {
        currentTask = t;
        renderTask();
    }

    private static class TaskCategoryLabel extends TaskLabel {
        public TaskCategoryLabel(String str) {
            super(str);
        }
    }


}
