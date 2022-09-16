package duke.gui.statistics.barchart;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Collections;

import duke.exception.DukeException;
import duke.gui.Response;
import duke.gui.ResponseType;
import duke.task.Task;
import duke.task.TaskList;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;

/**
 * Handles the generation of daily statistics bar graph.
 */
public abstract class DayBarGraph {
    private static final String NO_STAT = "hmmm... bobo has no statistics to show;"
            + " try completing some tasks first!";
    private static final String SHOW_STAT = "no problem, here are some interesting stats!"
            + "\nthis bar graph shows the tasks you've completed on %s, broken down by hour~";

    /**
     * Generates completed task statistics for a specified day- broken down by hour.
     *
     * @param tasks The task list to generate the statistics from.
     * @param queryDate The date to generate the statistics for.
     * @return A Stats type Response, with the generated BarGraph as the response object.
     */
    public static Response<BarGraph> generateBarGraphDay(TaskList tasks, LocalDate queryDate) {
        TaskList completedOnDate = tasks.filter(task ->
                task.isCompleted()
                        && task.getCompletionDateTime().toLocalDate().equals(queryDate)
        );

        int numCompletedTaskOnDay = completedOnDate.size();
        if (numCompletedTaskOnDay == 0) {
            return new Response<>(ResponseType.ERROR, NO_STAT);
        }

        Integer[] hashedDates = hashHours(completedOnDate);
        int maxTasksInHour = Collections.max(Arrays.asList(hashedDates));
        BarGraph result = new BarGraph(maxTasksInHour, (BarChart<String, Integer> b)
                -> populateDayBarChart(hashedDates, b));

        String successMessage = String.format(SHOW_STAT, queryDate);
        return new Response<>(ResponseType.STAT, successMessage, result);
    }

    private static Integer[] hashHours(TaskList completedOnDate) {
        Integer[] arrayList = new Integer[24];
        Arrays.fill(arrayList, 0);
        int numCompletedTaskOnDay = completedOnDate.size();
        for (int i = 0; i < numCompletedTaskOnDay; i++) {
            Task task;
            try {
                task = completedOnDate.getTask(i + 1);
            } catch (DukeException e) {
                continue;
            }
            // hash the completed task by the hour the task was completed
            LocalTime completedTime = task.getCompletionDateTime().toLocalTime();
            int completedTimeHour = completedTime.getHour();
            arrayList[completedTimeHour] += 1;
        }
        return arrayList;
    }

    private static BarChart<String, Integer> populateDayBarChart(Integer[] hashedHours,
                                                                BarChart<String, Integer> dayBarChart) {
        final int numHoursInDay = 24;
        XYChart.Series<String, Integer> series = new XYChart.Series<>();
        for (int i = 0; i < numHoursInDay; i++) {
            String hourString = (i < 10 ? "0" + i : i) + ":00";
            int numTasksInHour = hashedHours[i];
            series.getData().add(new XYChart.Data<>(hourString, numTasksInHour));
        }
        dayBarChart.getData().add(series);
        return dayBarChart;
    }
}
