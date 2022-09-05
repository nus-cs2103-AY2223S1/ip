package gibson.task;

/**
 * Represents a task with a task string that describes a job to be done
 */
public class Task {
    private String taskString;
    private boolean done;

    /**
     * Constructs a Task object that is represented by a task string which describes the task.
     * @param taskString the task
     */
    public Task(String taskString) {
        if (taskString.isBlank()) {
            throw new IllegalArgumentException("Task description cannot be blank.");
        }
        this.taskString = taskString;
        this.done = false;
    }

    public void mark() {
        this.done = true;
    }

    public void unmark() {
        this.done = false;
    }

    public char getChar() {
        return 'T';
    }

    public boolean taskContains(String s) {
        return taskString.contains(s);
    }

    public String getTaskString() {
        return taskString;
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof Task) {
            Task task = (Task) object;
            boolean isTaskStringSame = taskString.equals(task.taskString);
            boolean isCharSame = getChar() == task.getChar();
            return isTaskStringSame && isCharSame;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        if (done) {
            return "[" + getChar() + "][X] " + taskString;
        } else {
            return "[" + getChar() + "][ ] " + taskString;
        }
    }
}
