package duke.task;

/**
 * Task with TaskType, description of task and boolean to check whether the task is done yet.
 */
public class Task {
    private TaskType taskType;
    private String name;
    private boolean isMarked;

    /**
     * Constructor for Task.
     *
     * @param taskType Type of task
     * @param name Description of task
     * @param isMarked Denotes whether the task is done yet
     */
    public Task(TaskType taskType, String name, boolean isMarked) {
        this.taskType = taskType;
        this.name = name;
        this.isMarked = isMarked;
    }

    /**
     * @return TaskType of task
     */
    public TaskType getTaskType() {
        return taskType;
    }

    /**
     * @return Name of task
     */
    public String getName() {
        return name;
    }

    /**
     * @return Boolean isMarked of task
     */
    public boolean isMarked() {
        return isMarked;
    }

    /**
     * Mark the task as done
     */
    public void setMarked() {
        isMarked = true;
    }

    /**
     * Unmark the task as done
     */
    public void setUnmarked() {
        isMarked = false;
    }

    /**
     * String of task with information about the TaskType, description of task, isMarked
     * and time if the task type is Deadline or Event.
     * @return String to show details of task
     */
    @Override
    public String toString() {
        if (isMarked) {
            return "[X] " + name;
        } else {
            return "[ ] " + name;
        }
    }
}

