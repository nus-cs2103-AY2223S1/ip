package duke.scheduler;

import duke.task.TaskList;

/**
 * Scheduler class that creates various types of schedules.
 */
public abstract class Schedule {
    private final TaskList taskList;

    /**
     * Creates an instance of Schedule
     * @param taskList list of tasks
     */
    public Schedule(TaskList taskList) {
        this.taskList = taskList;
    }

    /**
     * Finds all tasks that are undone with specified requirements.
     * @return
     */
    public abstract TaskList getSchedule();
}
