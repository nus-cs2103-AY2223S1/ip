package duke.scheduler;

import java.time.LocalDateTime;

import duke.task.TaskList;

/**
 * Scheduler class for schedules of all types of tasks between date ranges
 */
public class RangeSchedule extends Schedule {
    private final TaskList taskList;
    private final LocalDateTime start;
    private final LocalDateTime end;

    /**
     * Constructs an instance of GeneralSchedule
     * @param taskList List of Tasks
     */
    public RangeSchedule(TaskList taskList, LocalDateTime start, LocalDateTime end) {
        super(taskList);
        this.taskList = taskList;
        this.start = start;
        this.end = end;
    }

    @Override
    public TaskList getSchedule() {
        return this.taskList.getTasksWithinDate(start, end);
    }
}
