public class DukeTask {
    String task;
    boolean isMarked;
    char taskType;

    DukeTask () {
        this.task = null;
        this.taskType = '?';
        this.isMarked = false;
    }

    DukeTask (String task) {
        this.task = task;
        this.taskType = '?';
        this.isMarked = false;
    }

    DukeTask (String task, boolean mark, char type) {
        this.task = task;
        this.taskType = type;
        this.isMarked = mark;
    }

    @Override
    public String toString() {
        return String.format("[%c][%c] %s", taskType, isMarked ? 'X' : ' ', task);
    }
}
