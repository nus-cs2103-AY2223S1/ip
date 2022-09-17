package duke.command;

import duke.Storage;
import duke.gui.Response;
import duke.gui.ResponseType;
import duke.gui.statistics.PieChart;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.ToDo;

/**
 * Represents a command to view the statistics of all completed tasks in a pie chart.
 */
public class PieChartStatsCommand extends StatsCommand {
    private static final String SHOW_STAT = "blob blob... here are some interesting stats!"
            + " this pie chart shows a breakdown of all your tasks ever completed by task type~";
    private static final String NO_STAT = "hmmm... bobo has no statistics to show;"
            + " try completing some tasks first!";

    /**
     * Generates a pie chart based on the type of tasks completed.
     *
     * @param tasks The task list the task is to be added to.
     * @param storage The storage manager that deals with loading and saving tasks to the hard disk.
     * @return Stat task Response containing the generated stat.
     */
    @Override
    public Response<PieChart> execute(TaskList tasks, Storage storage) {
        TaskList completedTasks = tasks.filter(Task::isCompleted);
        int totalCompletedTasks = completedTasks.size();
        if (totalCompletedTasks == 0) {
            return new Response<>(ResponseType.ERROR, NO_STAT);
        }
        int numToDo = completedTasks.filter(task -> task instanceof ToDo).size();
        int numDeadline = completedTasks.filter(task -> task instanceof Deadline).size();
        int numEvent = completedTasks.filter(task -> task instanceof Event).size();
        PieChart pieChart = new PieChart(totalCompletedTasks, numToDo, numDeadline, numEvent);
        return new Response<>(ResponseType.STAT, SHOW_STAT, pieChart);
    }
}
