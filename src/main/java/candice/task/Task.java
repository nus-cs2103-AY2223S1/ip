package candice.task;

/**
 * Encapsulates all relevant information of a task, namely the type of task, whether they have been finished and the
 * name of the task.
 */
public abstract class Task {
    /** Boolean to represent if the task has been finished. False by default */
    boolean finished = false;
    /** Name of the task */
    final String taskName;

    /**
     * Constructor of a Task object that encapsulates a task.
     *
     * @param taskName The name of the task.
     */
    public Task(String taskName) {
        this.taskName = taskName;
    }

    public void setFinished() {
        this.finished = true;
    }

    public void setUnfinished() {
        this.finished = false;
    }

    /**
     * Returns a String indicating if a task has been finished and the name of the task.
     *
     * @return A String containing a box with an "X" in it if the task has been finished and empty otherwise as well as
     * the name of the task.
     */
    public String getStatus() {
        String completed = this.finished ? "[X] " : "[ ] ";
        return completed + this.taskName;
    }

    public abstract String getStorageDescription();

    /**
     * Represents a Task object that does not have any date or time associated with it, namely a todo task.
     */
    public static class ToDo extends Task {
        /**
         * Constructor for a task with no date or time associated with it.
         *
         * @param taskName The name of the task.
         */
        public ToDo(String taskName) {
            super(taskName);
        }

        /**
         * Returns a String indicating that this Task object encapsulates a todo task and the status of the Task.
         *
         * @return A String indicating that it is a todo task, whether it has been finished and the name of the task.
         */
        @Override
        public String getStatus() {
            return "[T]" + super.getStatus();
        }

        /**
         * Returns a String representing all the relevant information of a todo task, in the format of type of task,
         * whether the task has been finished and the name of the task, to be placed in a text file as storage.
         *
         * @return A String containing the type of task, whether it has been finished and the name of the task in the
         * storage format.
         */
        @Override
        public String getStorageDescription() {
            String finishedStatus = this.finished ? "finished" : "unfinished";
            return "[T], " + finishedStatus + ", " + this.taskName + "\n";
        }
    }
}
