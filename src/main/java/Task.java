import java.time.LocalDateTime;

public class Task {
    String taskName;
    boolean done;

    public Task(String taskName) {
        this.taskName = taskName;
        this.done = false;
    }

    public void mark() {
        this.done = true;
    }

    public void unMark() {
        this.done = false;
    }


}
