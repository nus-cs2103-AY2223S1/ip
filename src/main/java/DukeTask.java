import java.time.LocalDateTime;

public class DukeTask {
    String task;
    boolean isMarked;
    char taskType;
    String time;
    LocalDateTime ldt;

    DukeTask () {
        this.task = null;
        this.taskType = '?';
        this.isMarked = false;
        this.time = "";
        this.ldt = null;
    }

    DukeTask (String task) {
        this.task = task;
        this.taskType = '?';
        this.isMarked = false;
        this.time = "";
        this.ldt = null;
    }

    DukeTask (String task, boolean mark, char type) {
        this.task = task;
        this.taskType = type;
        this.isMarked = mark;
        this.time = "";
        this.ldt = null;
    }

    DukeTask (String task, boolean mark, char type, String time) {
        this.task = task;
        this.taskType = type;
        this.isMarked = mark;
        this.time = time;
        this.ldt = null;
    }

    DukeTask (String task, boolean mark, char type, LocalDateTime ldt) {
        this.task = task;
        this.taskType = type;
        this.isMarked = mark;
        this.time = "";
        this.ldt = ldt;
    }

    @Override
    public String toString() {
        return String.format("[%c][%c] %s%s", taskType, isMarked ? 'X' : ' ', task, ldt == null ? time : "(" + ldt.toString() + ")");
    }
}
