package task;

/**
 * Represent a generic task
 */
public class DukeTask {
    private String task;
    private boolean isMarked;
    private char taskType;

    public DukeTask () {
        this.task = null;
        this.taskType = '?';
        this.isMarked = false;
    }

    public DukeTask (String task) {
        this.task = task;
        this.taskType = '?';
        this.isMarked = false;
    }

    public DukeTask (String task, boolean mark, char type) {
        this.task = task;
        this.taskType = type;
        this.isMarked = mark;
    }

    /**
     * Print out a format of the task
     * @return String
     */
    @Override
    public String toString () {
        return String.format("[%c][%c] %s", taskType, isMarked ? 'X' : ' ', task);
    }

    /**
     * Print out a format of the task. This is for file save use.
     * @return String
     */
    public String toStringSaveFile () {
        return this.taskType + "/" + (this.isMarked ? 'X' : 'O') + "/" + this.task;
    }

    /**
     * set task as marked or unmarked
     * @param mark
     */
    public void setMark (boolean mark) {
        this.isMarked = mark;
    }

    /**
     * Return the task type of task
     * @return char
     */
    public char getTaskType () {
        return this.taskType;
    }
}
