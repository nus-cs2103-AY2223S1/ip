package duke.gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.shape.Arc;

import java.io.IOException;

public class PieChart extends HBox {
    @FXML
    private Arc todo;
    @FXML
    private Arc deadline;
    @FXML
    private Arc event;

    public PieChart(double totalCompleted, double toDoCompleted, double deadlineCompleted, double eventCompleted) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/PieChart.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (totalCompleted == 0) {
            return;
        }
        double toDoAngle = (toDoCompleted / totalCompleted) * 360;
        double deadlineAngle = (deadlineCompleted / totalCompleted) * 360;
        double eventAngle = (eventCompleted / totalCompleted) * 360;

        todo.setLength(toDoAngle);
        deadline.setLength(deadlineAngle);
        event.setLength(eventAngle);

        todo.setStartAngle(0);
        deadline.setStartAngle(toDoAngle);
        event.setStartAngle(-eventAngle);
        this.setMinHeight(Region.USE_PREF_SIZE);
    }
}
