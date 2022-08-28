package task;

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

    @Override
    public String toString () {
        return String.format("[%c][%c] %s", taskType, isMarked ? 'X' : ' ', task);
    }

    public String toStringSaveFile () {
        return this.taskType + "/" + (this.isMarked ? 'X' : 'O') + "/" + this.task;
    }

    public void setMark (boolean mark) {
        this.isMarked = mark;
    }

    public char getTaskType () {
        return this.taskType;
    }
}
