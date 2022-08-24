import java.time.LocalDateTime;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }
    
    public abstract String encodeToString();
    
    public static Task decodeFromString(String line) {
        String[] strings = line.split("\\|");
        switch (strings[0]) {
        case "T":
            return new Todo(strings[2], strings[1].equals("X"));
        case "D":
            return new Deadline(strings[2], strings[3], strings[1].equals("X"));
        case "E":
            return new Event(strings[2], strings[3], strings[1].equals("X"));
        default:
            return null;
        }
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
