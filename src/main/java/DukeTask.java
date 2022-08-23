public class DukeTask {
    String task;
    boolean isMarked;
    char taskType;
    String time;

    DukeTask () {
        this.task = null;
        this.taskType = '?';
        this.isMarked = false;
        this.time = "";
    }

    DukeTask (String task) {
        this.task = task;
        this.taskType = '?';
        this.isMarked = false;
        this.time = "";
    }

    DukeTask (String task, boolean mark, char type) {
        this.task = task;
        this.taskType = type;
        this.isMarked = mark;
        this.time = "";
    }

    DukeTask (String task, boolean mark, char type, String time) {
        this.task = task;
        this.taskType = type;
        this.isMarked = mark;
        this.time = time;
    }

    @Override
    public String toString() {
        return String.format("[%c][%c] %s%s", taskType, isMarked ? 'X' : ' ', task, time.isBlank() ? "" : time);
    }
}
