package duke.scheduler;

import duke.task.TaskList;

/**
 * Scheduler class for schedules of all types of tasks
 */
public class GeneralSchedule extends Schedule {
    private final TaskList taskList;

    /**
     * Constructs an instance of GeneralSchedule
     * @param taskList List of Tasks
     */
    public GeneralSchedule(TaskList taskList) {
        super(taskList);
        this.taskList = taskList;
    }

    @Override
    public TaskList getSchedule() {
        return this.taskList.sortTaskListByDate(taskList.getTaskList());
    }
}
