package duke.scheduler;

import duke.task.TaskList;

/**
 * Scheduler class that creates various types of schedules.
 */
public abstract class Schedule {
    private final TaskList taskList;

    public Schedule(TaskList taskList) {
        this.taskList = taskList;
    }

    public abstract TaskList getSchedule();
}
