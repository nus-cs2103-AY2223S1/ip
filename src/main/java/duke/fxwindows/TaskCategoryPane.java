package duke.fxwindows;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import java.io.IOException;

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
                new Label("All"),
                new Label("TODOs"),
                new Label("Events"),
                new Label("Deadline")
        );

        HBox.setHgrow(this, Priority.ALWAYS);
    }

}
