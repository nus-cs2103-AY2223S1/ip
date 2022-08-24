import java.time.LocalDateTime;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }
    
    public static LocalDateTime decodeDateTime(String s) {
        String[] dtStrings = s.split(" ");
        String date = dtStrings[0];

        String[] dateArr = date.split("/");
        String time = dtStrings[1];

        LocalDateTime dt = LocalDateTime.of(
                Integer.parseInt(dateArr[2]),
                Integer.parseInt(dateArr[1]),
                Integer.parseInt(dateArr[0]),
                Integer.parseInt(time.substring(0, 2)),
                Integer.parseInt(time.substring(2, 4)),
                0);
        
        return dt;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void mark() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
