package duke.command;

import java.time.LocalDate;
import java.util.function.Function;

import duke.Storage;
import duke.date.DateTimeParse;
import duke.exception.DukeException;
import duke.gui.Response;
import duke.gui.statistics.barchart.BarGraph;
import duke.gui.statistics.barchart.DayBarGraph;
import duke.gui.statistics.barchart.WeekBarGraph;
import duke.task.TaskList;

/**
 * Represents a command that generates a bar graph statistic.
 */
public class BarGraphStatsCommand extends StatsCommand {
    private Function<TaskList, Response<BarGraph>> barGraphResponseConstructor;

    /**
     * Constructs a BarGraphStatsCommand. If no flag is provided, will default to a
     * day bar graph. If no date is specified, will default to the current date.
     *
     * @param constructString The user input flags used to specify the type of bar graph
     *                        (day, week) to construct. Also may specify the date for
     *                        which the statistic is to be generated for.
     * @throws DukeException If the provided flag is not supported, or if the provided
     *                       date format is not recognised.
     */
    public BarGraphStatsCommand(String constructString) throws DukeException {
        constructString = constructString.trim();
        // if no flags for the type of bar graph are specified,
        // assume that it is daily stat type bar graph
        if (constructString.isBlank()) {
            barGraphResponseConstructor = (TaskList t) ->
                    DayBarGraph.generateBarGraphDay(t, LocalDate.now());
            return;
        }

        if (constructString.startsWith("/day")) {
            String dateQueryString = constructString.substring(4);
            constructDayBarGraph(dateQueryString);
        } else if (constructString.startsWith("/week")) {
            String dateQueryString = constructString.substring(5);
            constructWeekBarGraph(dateQueryString);
        } else {
            constructDayBarGraph(constructString);
        }
    }

    private void constructDayBarGraph(String dateQueryString) throws DukeException {
        if (dateQueryString.isBlank()) {
            // if no date is specified, assume the current date
            barGraphResponseConstructor = (TaskList t) ->
                    DayBarGraph.generateBarGraphDay(t, LocalDate.now());
        } else {
            dateQueryString = dateQueryString.trim();
            LocalDate dateQuery = DateTimeParse.parseDateTime(dateQueryString).toLocalDate();
            barGraphResponseConstructor = (TaskList t) ->
                    DayBarGraph.generateBarGraphDay(t, dateQuery);
        }
    }

    private void constructWeekBarGraph(String dateQueryString) throws DukeException {
        if (dateQueryString.isBlank()) {
            // if no date is specified, assume the current date
            barGraphResponseConstructor = (TaskList t) ->
                    WeekBarGraph.generateBarGraphWeek(t, LocalDate.now());
        } else {
            dateQueryString = dateQueryString.trim();
            LocalDate dateQuery = DateTimeParse.parseDateTime(dateQueryString).toLocalDate();
            barGraphResponseConstructor = (TaskList t) ->
                    WeekBarGraph.generateBarGraphWeek(t, dateQuery);
        }
    }

    /**
     * Generates a bar graph based on the type of tasks completed.
     *
     * @param tasks The task list the task is to be added to.
     * @param storage The storage manager that deals with loading and saving tasks to the hard disk.
     * @return Stat task Response containing the generated stat.
     */
    @Override
    public Response<BarGraph> execute(TaskList tasks, Storage storage) {
        return barGraphResponseConstructor.apply(tasks);
    }
}
