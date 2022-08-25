public class ParsedData {
    private boolean isDone;
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

    public ParsedData(String status, String task, String during, String time) {
        this.task = task;
        this.during = during;
        this.time = time;
        this.isDone = status.equals("X");
    }

    public ParsedData(String task) {
        this.task = task;
    }

    public ParsedData(int listIndex) {
        this.listIndex = listIndex;
    }

    public String getTaskName() {
        return this.task;
    }

    public String getDuring() {
        return this.during;
    }

    public String getTime() {
        return this.time;
    }

    public boolean getStatus() {
        return this.isDone;
    }

    public int getListIndex() {
        return listIndex;
    }
}
