package duke.ui;

import java.io.IOException;

import duke.task.TaskList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;

/**
 * Allow tasks to be grouped by schdule
 */
public class ScheduleView extends VBox {

    @FXML
    private ListView<String> myListView;

    private ScheduleView(TaskList tasks) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/ScheduleView.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        myListView.getItems().addAll(tasks.getScheduleView());
    }

    public static ScheduleView getScheduleView(TaskList tasks) {
        return new ScheduleView(tasks);
    }
}
