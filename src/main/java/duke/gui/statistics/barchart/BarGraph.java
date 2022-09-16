package duke.gui.statistics.barchart;

import java.io.IOException;
import java.util.function.Function;

import duke.gui.MainWindow;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.layout.HBox;

/**
 * Represents a bar graph statistic, used to visualise the number of tasks completed
 * in a day, or in a week.
 */
public class BarGraph extends HBox {
    @FXML
    private BarChart<String, Integer> barChart;
    @FXML
    private CategoryAxis xAxis;
    @FXML
    private NumberAxis yAxis;

    /**
     * Constructs the bar graph.
     *
     * @param maxTasks The maximum number of tasks.
     * @param constructBarChart The function that populates the bar graph with the data.
     */
    protected BarGraph(int maxTasks, Function<BarChart<String, Integer>,
                BarChart<String, Integer>> constructBarChart) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/BarGraph.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        yAxis.setUpperBound(maxTasks + 1);
        yAxis.setTickUnit(1);
        constructBarChart.apply(barChart);
    }
}
