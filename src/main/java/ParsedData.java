import java.time.LocalDateTime;

public class ParsedData {
    private String task;
    private String during;
    private String timeText;
    private LocalDateTime dateTime;
    private int listIndex;

    public ParsedData(String task, String during, String time) {
        this.task = task;
        this.during = during;
        this.timeText = time;
    }

    public ParsedData(String task, String during, LocalDateTime dateTime) {
        this.task = task;
        this.during = during;
        this.dateTime = dateTime;
    }

    public ParsedData(String task) {
        this.task = task;
    }

    public ParsedData(int listIndex) {
        this.listIndex = listIndex;
    }

    public String getTaskName() {
        return task;
    }

    public String getDuring() {
        return during;
    }

    public String getTimeText() {
        return timeText;
    }

    public int getListIndex() {
        return listIndex;
    }
}
