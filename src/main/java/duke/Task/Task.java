package duke.Task;
import java.time.LocalDateTime;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, Boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void toggleStatus() {
        if (this.isDone) {
            this.isDone = false;
        } else {
            this.isDone = true;
        }
    }

    public String formatTask() {
        return String.format("[T] [%s] %s", this.getStatusIcon(), this.description);
    }

    public String formatTaskString() {
        return String.format("T|%s|%s", this.isDone, this.description);
    }

    public static Task MakeTask(String type, Boolean isDone, String description, LocalDateTime dateTime) {


//        System.out.println(info[0]);
//        System.out.println(info[1]);
//        System.out.println(info[2]);
//        System.out.println(info[3]);

        if (type.equals("D")) {
            return new Deadline(isDone, description, dateTime);
        } else if (type.equals("E")) {
            return new Event(isDone, description, dateTime);
        }
        return new Task(description, isDone);
    }

}
