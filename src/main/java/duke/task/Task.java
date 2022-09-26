package duke.task;

import duke.duke.DukeException;

/** Asbstract class that represents Task */
public abstract class Task {
    private String taskName;
    private boolean isDone;
    private Priority priority;

    /**
     * Returns a Task object.
     * Initialises the Task with taskName variable and marks the Task as not done initially.
     *
     * @param taskName The name given to the task.
     */
    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
        this.priority = Priority.LOW;
    }

    /**
     * Enum that represents the priority levels of the tasks.
     */
    public enum Priority {
        HIGH(1), LOW(3), MEDIUM(2);
        private int value;
        Priority(int value) {
            this.value = value;
        }
        public int getValue() {
            return value;
        }
        public String getName() {
            if (this.value == 1) {
                return "High";
            } else if (this.value == 2) {
                return "Medium";
            } else {
                return "Low";
            }
        }
    }

    /**
     * Sets priority level of task with given priority.
     *
     * @param priority Priority enum that represents priority level of task.
     */
    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    /**
     * Sets priority level of task with given priority number.
     *
     * @param priorityNum Priority number that represents priority level of task.
     */
    public void setPriority(int priorityNum) {
        if (priorityNum == 1) {
            this.priority = Priority.HIGH;
        } else if (priorityNum == 2) {
            this.priority = Priority.MEDIUM;
        } else if (priorityNum == 3) {
            this.priority = Priority.LOW;
        }
    }

    /**
     * Sets priority level of task with given priority string.
     *
     * @param priorityString Priority String that represents priority level of task.
     */
    public void setPriority(String priorityString) throws DukeException {
        String name = priorityString.toLowerCase().replaceAll(" ", "");
        switch (name) {
        case "high":
            this.priority = Priority.HIGH;
            break;
        case "medium":
            this.priority = Priority.MEDIUM;
            break;
        case "low":
            this.priority = Priority.LOW;
            break;
        default:
            throw new DukeException("OOPS!!! Invalid priority level");
        }
    }

    /**
     * Gets priority level string representation of task.
     *
     * @return String that represents priority level of task.
     */
    public String getPriorityString() {
        return this.priority.getName();
    }

    /**
     * Gets priority level number representation of task.
     *
     * @return Integer that represents priority level of task.
     */
    public int getPriorityNumber() {
        return this.priority.value;
    }

    /**
     * Sets the status of the task according to the boolean parameter.
     *
     * @param isDone True to mark task as done and false to mark task as not done
     */
    public void setTaskStatus(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Returns the name of the task.
     *
     * @return Name of the task.
     */
    public String getTaskName() {
        return this.taskName;
    }

    /**
     * Returns a boolean on whether the task is done.
     *
     * @return True if the task is done, false if the task is not done.
     */
    public Boolean getTaskStatus() {
        return this.isDone;
    }

    /**
     * Returns the string representation of the task to be printed to user.
     *
     * @return String representation of the task to be printed to user.
     */
    @Override
    public String toString() {
        return (this.getTaskStatus() ? "[X]" : "[ ]") + "[" + this.getPriorityString() + "] " + this.getTaskName();
    }

    /**
     * Returns the string representation of the task that is used to save into text file
     *
     * @return String representation of the task that is used to save into text file.
     */
    public abstract String toFileString();

}
