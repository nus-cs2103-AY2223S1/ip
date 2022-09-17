package duke.gui.statistics.barchart;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Arrays;
import java.util.Collections;
import java.util.EnumSet;
import java.util.Locale;

import duke.exception.DukeException;
import duke.gui.Response;
import duke.gui.ResponseType;
import duke.task.Task;
import duke.task.TaskList;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;

/**
 * Handles the generation of weekly statistics bar graph.
 */
public abstract class WeekBarGraph {
    private static final String NO_STAT = "hmmm... bobo has no statistics to show;"
            + " try completing some tasks first!";
    private static final String SHOW_STAT = "blob blob... doing some calculations..."
            + "\nhere are some interesting stats! this bar chart shows the tasks you've"
            + " completed in the week from %s to %s, broken down into days~";
    private static final EnumSet<DayOfWeek> daysOfWeek = EnumSet.allOf(DayOfWeek.class);

    /**
     * Generates completed task statistics for a specified week- broken down by day.
     *
     * @param tasks The task list to generate the statistics from.
     * @param queryDate The date to generate the statistics for (the week the date falls in).
     * @return A Stats type Response, with the generated BarGraph as the response object.
     */
    public static Response<BarGraph> generateBarGraphWeek(TaskList tasks, LocalDate queryDate) {
        LocalDate weekStart = queryDate.with(DayOfWeek.MONDAY);
        LocalDate weekEnd = queryDate.with(DayOfWeek.SUNDAY);
        TaskList completedInWeek = tasks.filter(task -> {
            if (!task.isCompleted()) {
                return false;
            }
            LocalDate completionDate = task.getCompletionDateTime().toLocalDate();
            return !completionDate.isBefore(weekStart) && !completionDate.isAfter(weekEnd);
            }
        );

        int numCompletedTaskInWeek = completedInWeek.size();
        if (numCompletedTaskInWeek == 0) {
            return new Response<>(ResponseType.ERROR, NO_STAT);
        }

        Integer[] hashedDates = hashHours(completedInWeek);
        int maxTasksInDay = Collections.max(Arrays.asList(hashedDates));
        BarGraph result = new BarGraph(maxTasksInDay, (BarChart<String, Integer> b)
                -> populateDayBarChart(hashedDates, b));

        String successMessage = String.format(SHOW_STAT, weekStart, weekEnd);
        return new Response<>(ResponseType.STAT, successMessage, result);
    }

    private static Integer[] hashHours(TaskList completedInWeek) {
        Integer[] arrayList = new Integer[7];
        Arrays.fill(arrayList, 0);
        int numCompletedTaskInWeek = completedInWeek.size();
        for (int i = 0; i < numCompletedTaskInWeek; i++) {
            Task task;
            try {
                task = completedInWeek.getTask(i + 1);
            } catch (DukeException e) {
                continue;
            }
            // hash the completed task by the day of the week the task was completed
            int completedDay = task.getCompletionDateTime().getDayOfWeek().getValue(); // 1-indexed
            arrayList[completedDay - 1] += 1;
        }
        return arrayList;
    }

    private static BarChart<String, Integer> populateDayBarChart(Integer[] hashedHours,
                                                                 BarChart<String, Integer> dayBarChart) {
        XYChart.Series<String, Integer> series = new XYChart.Series<>();
        int dayOfWeekCounter = 0;
        for (DayOfWeek dayOfWeek : daysOfWeek) {
            String dayOfWeekString = dayOfWeek.getDisplayName(TextStyle.FULL, Locale.UK);
            int numTasksInDay = hashedHours[dayOfWeekCounter];
            series.getData().add(new XYChart.Data<>(dayOfWeekString, numTasksInDay));
            dayOfWeekCounter++;
        }
        dayBarChart.getData().add(series);
        return dayBarChart;
    }
}
