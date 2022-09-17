package duke.gui.statistics;

import java.io.IOException;

import duke.gui.MainWindow;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.shape.Arc;

/**
 * Represents a pie chart for statistics breakdown of type of task completed.
 */
public class PieChart extends HBox {
    @FXML
    private Arc todo;
    @FXML
    private Arc deadline;
    @FXML
    private Arc event;
    @FXML
    private Label todoCount;
    @FXML
    private Label deadlineCount;
    @FXML
    private Label eventCount;

    /**
     * Creates a pie chart to view the breakdown of tasks completed, given their statistics.
     *
     * @param totalCompleted The total number of tasks completed.
     * @param toDoCompleted The total number of to do tasks completed.
     * @param deadlineCompleted The total number of deadline tasks completed.
     * @param eventCompleted The total number of event tasks completed.
     */
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

        todoCount.setText((int) toDoCompleted + "");
        deadlineCount.setText((int) deadlineCompleted + "");
        eventCount.setText((int) eventCompleted + "");
        this.setMinHeight(Region.USE_PREF_SIZE);
    }
}
