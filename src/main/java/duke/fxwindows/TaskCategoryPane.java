package duke.fxwindows;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import java.io.IOException;

import static java.lang.Double.MAX_VALUE;

public class TaskCategoryPane extends VBox {


    TaskCategoryPane() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/TaskCategoryPane.fxml"));
            fxmlLoader.setController(this);
//            fxmlLoader.setRoot(this);
            fxmlLoader.load();

        } catch (IOException e) {
            e.printStackTrace();
        }

        this.getChildren().addAll(
                new TaskCategoryLabel("All"),
                new TaskCategoryLabel("TODOs"),
                new TaskCategoryLabel("Events"),
                new TaskCategoryLabel("Deadline")
        );

//        HBox.setHgrow(this, Priority.ALWAYS);

        this.getStyleClass().add("pane");
        this.getStyleClass().add("categoryPane");
    }

    private static class TaskCategoryLabel extends TaskLabel {
        public TaskCategoryLabel(String str) {
            super(str);
            this.getStyleClass().add("hoverableText");
        }
    }

}
